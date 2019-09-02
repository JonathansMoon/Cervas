package com.cervas.controller;

import javax.validation.Valid;

import com.cervas.model.Estilo;
import com.cervas.service.CadastroEstiloService;
import com.cervas.service.exception.NomeEstiloJaCadastradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EstilosController {

    @Autowired
    private CadastroEstiloService cadastroEstiloService;

    @RequestMapping("/estilos/novo")
    public ModelAndView novo(Estilo estilo) {
        
        ModelAndView mv = new ModelAndView("cerveja/CadastroEstilo");
        return mv;
    }

    @RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {

        System.out.println(estilo.getNome());
        if (result.hasErrors()) {
            return novo(estilo);
        }

        try {
            cadastroEstiloService.salvar(estilo);
        } catch (NomeEstiloJaCadastradoException e) {
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return novo(estilo);
        }
        
        attributes.addFlashAttribute("messagem" , "Estilo salvo com Sucesso");
        return new ModelAndView("redirect:/estilos/novo");
    }
    
}