package com.dws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaceController {

    @Autowired
    PlaceHolder placeHolder;

    @GetMapping("/")
    public String getSitios(Model model){
        model.addAttribute("sitios", placeHolder);
        return "tablon";  //da error aqui no sabemos por que
    }

    @RequestMapping("/place/tourism")
    public String tourism(Model model){
        return "tourism_greeting";
    }

    @RequestMapping("/place/restaurants")
    public String restaurant(Model model){
        return "restaurants_greeting";
    }

    @RequestMapping("/place/leisure")
    public String leisure(Model model){
        return "leisure_greeting";
    }
}