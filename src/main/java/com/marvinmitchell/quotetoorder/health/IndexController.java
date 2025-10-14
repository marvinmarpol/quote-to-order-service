package com.marvinmitchell.quotetoorder.health;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private Environment environment;

    @RequestMapping("/health")
    public String index(Model model) {
        Date now = new Date();
        model.addAttribute("status", "OK - " + now.toString());

        return "index";
    }

}
