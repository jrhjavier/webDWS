package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaceController {

    @Autowired
    EventHolder eventHolder;

    @GetMapping("/")
    public String getSitios(Model model){
        model.addAttribute("sitios", eventHolder);
        return "tablon";  //da error aqui no sabemos por que
    }
}