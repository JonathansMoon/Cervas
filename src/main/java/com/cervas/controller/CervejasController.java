package com.cervas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cervas.model.Cerveja;
import com.cervas.repository.Cervejas;

@Controller
public class CervejasController {
	
	@Autowired
	private Cervejas cervejas;
	
	@RequestMapping("/cervejas/novo")
	//A view precisa do objeto Cerveja
	public String novo(Cerveja cerveja) {
		cervejas.findAll();
		
		return "cerveja/CadastroCerveja";
	}

	/**
	 * 
	 * @param cerveja é o mesmo que o Request request, só que específico.
	 * @param result BindingResult é um objeto que guarda infomações de erro
	 * @return
	 */
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		attributes.addFlashAttribute("mensagem" , "Cerveja salva com Sucesso");
		return "redirect:/cervejas/novo";
	}

	@RequestMapping("/clientes/novo")
	//A view precisa do objeto Cerveja
	public String novoCliente() {
		return "cerveja/CadastroCliente";
	}

	@RequestMapping("/usuarios/novo")
	//A view precisa do objeto Cerveja
	public String novoUsuario() {
		return "cerveja/CadastroUsuario";
	}

}
