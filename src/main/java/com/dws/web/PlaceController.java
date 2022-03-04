package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class PlaceController {

    @Autowired
    PlaceHolder placeHolder;

    @GetMapping("/")
    public String getSitios(Model model){
        model.addAttribute("sitios", placeHolder);
        return "tablon";  //da error aqui no sabemos por que
    }
}