package br.com.lucasteixeira.vilacontrol.controllers;

import br.com.lucasteixeira.vilacontrol.models.Residencia;
import br.com.lucasteixeira.vilacontrol.service.ResidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private ResidenciaService residenciaService;

    //HOME
    @RequestMapping("/")
    public ModelAndView index(){
        List<Residencia> residencias = residenciaService.todasResidencias();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("residencias", residencias);
        return mv;
    }

    //LOGIN
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }

}
