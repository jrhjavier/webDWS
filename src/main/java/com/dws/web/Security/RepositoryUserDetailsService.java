package com.dws.web.Security;

import com.dws.web.Customer.Customer;
import com.dws.web.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class RepositoryUserDetailsService implements UserDetailsService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{
        Customer c = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> roles = new ArrayList<>();

        for (String role : c.getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        //roles.add(new SimpleGrantedAuthority("ADMIN"));
        //¿Por que asi no?

        return new org.springframework.security.core.userdetails.User(c.getEmail(),
                c.getPasswd(), roles);
    }
}
