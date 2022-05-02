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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Override //Autorization
    protected void configure(HttpSecurity http) throws Exception{

        //Public pages
        http.authorizeRequests().antMatchers(
                "/css/**",
                "/js/**",
                "/images/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        //http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/event/**").permitAll();
        http.authorizeRequests().antMatchers("/events/**").permitAll();
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/customer/**").permitAll();
        http.authorizeRequests().antMatchers("/query/events/filtered").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
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
        //http.formLogin().failureUrl("/login?error=error");
        http.formLogin().failureUrl("/loginerror");


        //Log out:
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        //http.csrf().disable();
        //http.csrf().ignoringAntMatchers("/catalogo");
        //http.csrf().ignoringAntMatchers("/api/**");

    }
    @Bean //Declaramos una instancia, que podemos llamarla mas tarde con @Autowired (constructor de Costumer)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override //Authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}

