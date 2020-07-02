package com.cervas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cervas.model.Cidade;
import com.cervas.repository.Cidades;
import com.cervas.repository.Estados;
import com.cervas.service.CadastroCidadeService;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(cidade);
		}
		
		try {
			cadastroCidadeService.salvar(cidade);
		} catch (Exception e) {

		}
		
		return new ModelAndView("redirect:/cidades/novo");
	}

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {	
    	return cidades.findByEstadoCodigo(codigoEstado);
    }
}
