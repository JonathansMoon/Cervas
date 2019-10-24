package com.cervas.controller;

import javax.validation.Valid;

import com.cervas.model.Cerveja;
import com.cervas.model.Origem;
import com.cervas.model.Sabor;
import com.cervas.repository.Cervejas;
import com.cervas.repository.Estilos;
import com.cervas.service.CadastroCervejaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	@Autowired
	private Estilos estilos;

	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	@Autowired
	private Cervejas cervejas;
	
	@RequestMapping("/novo")
	//A view precisa do objeto Cerveja no Form
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}

	/**
	 * 
	 * @param cerveja é o mesmo que o Request request, só que específico.
	 * @param result BindingResult é um objeto que guarda infomações de erro
	 * @return
	 */
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem" , "Cerveja salva com Sucesso");
		return new ModelAndView("redirect:/cervejas/novo");
	}

	@GetMapping()
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("cervejas", cervejas.findAll());
		return mv;
	}

}
