package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public class CustomerController {

    @Autowired
    CustomerHolder customerHolder;

    /*
    @PostMapping("/events/add")  //Add an event to the planning
    public String addEventToPlanningAPI(@RequestBody Event e){
        return customerHolder.ad;  //no se como hacer esto
    }


     */
}
