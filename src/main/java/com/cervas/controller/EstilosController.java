package com.cervas.controller;



import com.cervas.model.Estilo;
import com.cervas.repository.Estilos;
import com.cervas.repository.filter.EstiloFilter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	public Estilos estilos;

    @RequestMapping
    public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result,
    		@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
    	
        
        ModelAndView mv = new ModelAndView("cerveja/EstiloPesquisa");
        mv.addObject("estilos", estilos.findAll());
        return mv;
    }    
}