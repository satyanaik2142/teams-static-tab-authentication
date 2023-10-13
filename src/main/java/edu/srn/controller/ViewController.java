package edu.srn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class ViewController {

    @GetMapping
    public String getHome(HttpServletRequest httpServletRequest, Model model){

        Long userMappingId = (Long)httpServletRequest.getSession().getAttribute("userMappingId");

        if(!(userMappingId != null && userMappingId != 0)){
            model.addAttribute("callbackURL", httpServletRequest.getRequestURI());
            return "ui/redirect";
        }

        return "ui/home";
    }
}
