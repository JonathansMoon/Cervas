package com.cervas.controller;



import com.cervas.model.Estilo;
import com.cervas.repository.Estilos;
import com.cervas.repository.filter.EstiloFilter;
import com.cervas.service.CadastroEstiloService;
import com.cervas.service.exception.NomeEstiloJaCadastradoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	public Estilos estilos;
	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;

    @RequestMapping
    public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result, 
    		@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
    	
        
        ModelAndView mv = new ModelAndView("cerveja/EstiloPesquisa");
        Page<Estilo> estiloOptional = estilos.filtrar(estiloFilter, pageable);
        mv.addObject("estilos", estiloOptional);
        System.out.println(">>> : " + estiloOptional);
        

        return mv;
    }  
    
    @RequestMapping("/novo")
    public ModelAndView novo(Estilo estilo) {
    	return new ModelAndView("cerveja/CadastroEstilo");
    }
    
    //Cadastro na pagina de estilo 
    @RequestMapping(value="/novo", method = RequestMethod.POST)
    public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
    	if(result.hasErrors()) {
    		return novo(estilo);
    	}
    	
    	try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
    	attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso");
    	return new ModelAndView("redirect:/estilos/novo");
    	
    }
    
    // @RequestBody transforma o corpo da requisição no objeto Estilo
    // Retorna um @ResponseBody ResponseEntity<?>
    @RequestMapping(value="", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
    	if(result.hasErrors()) {
    		return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
    	}
    	
    	try { System.out.println(estilo.getNome());
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    	
    	return ResponseEntity.ok(estilo);
    }
}