package com.cervas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cervas.model.Usuario;
import com.cervas.repository.Usuarios;
import com.cervas.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("usuarios", usuarios.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes ) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
			attributes.addFlashAttribute("messages" , "Cadastrado com sucesso");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ModelAndView();
	}
}
