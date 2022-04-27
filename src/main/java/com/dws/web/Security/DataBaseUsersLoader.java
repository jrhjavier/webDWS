package com.dws.web.Security;

import com.dws.web.Customer.Customer;
import com.dws.web.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseUsersLoader {

    @Autowired
    private CustomerRepository customerRepository;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
     */

    @PostConstruct
    private void initDatabase() {
        if (customerRepository.findByEmail("admin@admin.com") == null) {
            Customer ad = new Customer("Admin", "-", "admin@admin.com", "-", "admin123", "-");
            ad.makeAdmin();
            customerRepository.save(ad);
        }
        //customerRepository.save(new Customer("user", "user", "user@user.es", "626-206-725",  passwordEncoder.encode("pass"), "CasaUser", "USER"));
        //customerRepository.save(new Customer("admin", "admin", "admin@admin.es", "626-206-725", passwordEncoder.encode("adminpass"), "CasaAdmin", "USER", "ADMIN"));
    }


}
