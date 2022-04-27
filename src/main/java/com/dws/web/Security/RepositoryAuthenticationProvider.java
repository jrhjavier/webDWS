package com.dws.web.Security;

import java.util.ArrayList;
import java.util.List;

import com.dws.web.Customer.Customer;
import com.dws.web.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class RepositoryAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException{
        Customer user = customerRepository.findByEmail(auth.getName());
        String pass = auth.getCredentials().toString();
        if (user == null  || !new BCryptPasswordEncoder().matches(pass, user.getPasswd())) {
                throw new BadCredentialsException("Wrong credentials");
        }

            //Faltan los roles
        List<GrantedAuthority> roles = new ArrayList<>();
        for(String role : user.getRoles()){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPasswd(), roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
