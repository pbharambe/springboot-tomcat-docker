package com.techlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebApplicationController {

    @RequestMapping(value = "/index/{name}")
    public String welcome(Model model, @PathVariable("name") String name) {
        model.addAttribute("name", name);
        return "index";
    }
}
