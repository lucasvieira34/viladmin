package br.com.lucasteixeira.vilacontrol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    //HOME
    @RequestMapping("/")
    public String index(){
        return "home/index";
    }

//    //LOGIN
//    @GetMapping("/login")
//    public ModelAndView login(){
//        ModelAndView mv = new ModelAndView("login/login");
//        return mv;
//    }

}
