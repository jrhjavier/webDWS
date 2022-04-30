
package com.dws.web.Security;

import com.dws.web.Security.RepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@Order(value = 2)
public class CustomRestSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/api/login").authenticated();

        // Private endpoints

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/events/new").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/event/{idEvent}/review/new").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/customer/new").hasRole("USER");

        //Como hacer para que el admin pueda editar los usuarios pero que los usuarios solo puedan editar su perfil
        //Si pongo que el USER pueda crear usuarios hace falta que tambien ponga que los puede crear el admin

        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/admin/customer/update").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/admin/customer/update/{email}").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/event/{idEvent}/review/update/{idReview}").hasRole("USER");

        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/admin/delete/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/planning/delete/{idEvent}").hasRole("USER");

        http.authorizeRequests().antMatchers("/api/customer/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/api/event/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/api/events/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/api/planning/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/").hasRole("USER");

        // Other endpoints are public
        http.authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN");

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();

        // Enable Basic Authentication
        http.httpBasic();

        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session (because every request has credentials)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.logout().logoutSuccessHandler((rq, rs, a)->{});

    }


}
