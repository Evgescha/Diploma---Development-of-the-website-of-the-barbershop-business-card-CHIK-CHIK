package com.hescha.barbershop.controller;

import com.hescha.barbershop.service.MServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", "/index"})
public class IndexController {

    @Autowired
    MServiceService service;

    @GetMapping
    String getIndex(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "index";
    }

}
