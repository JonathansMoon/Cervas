package com.cervas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cervas.model.Cerveja;

@Controller
public class CervejasController {
	
	@RequestMapping("/cervejas/novo")
	public String novo(Model model) {
		model.addAttribute(new Cerveja());
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result) {
		
		// Result é um objeto que guarda infomações de erro
		if (result.hasErrors()) {
			return "cerveja/CadastroCerveja";
		}
		System.out.println(">>>> sku:" +cerveja.getSku());
		return "cerveja/CadastroCerveja";
	}

}
