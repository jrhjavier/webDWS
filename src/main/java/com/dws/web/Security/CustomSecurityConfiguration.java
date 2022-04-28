package com.dws.web.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
@Order(value = 1)
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //Public pages
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/event/**").permitAll();
        http.authorizeRequests().antMatchers("/events/**").permitAll();
        http.authorizeRequests().antMatchers("/customer/**").permitAll();
        http.authorizeRequests().antMatchers("/planning/**").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        /*
        http.authorizeRequests().antMatchers("/events/all/**").permitAll();
        http.authorizeRequests().antMatchers("/events").permitAll();
        http.authorizeRequests().antMatchers("/events/{idEvent}").permitAll();
        http.authorizeRequests().antMatchers("/events/filtered").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/catalogo/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/catalogo/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/producto/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/cart/items").permitAll();
        */

        // Reject the others
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        /*
        http.authorizeRequests().antMatchers("/customer/delete").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/customers").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/events/**").hasAnyRole("ADMIN");
        */


        //Log in:
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/login?error=error");


        //Log out:
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        //http.csrf().disable();
        //http.csrf().ignoringAntMatchers("/catalogo");
        //http.csrf().ignoringAntMatchers("/api/**");

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}

