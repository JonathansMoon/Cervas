package com.cervas.controller;

import javax.validation.Valid;

import com.cervas.model.Estilo;
import com.cervas.service.CadastroEstiloService;
import com.cervas.service.exception.NomeEstiloJaCadastradoException;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping(value = "/estilos", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE})
    // @RequestBody pega o corpo da requisição e transforma em objeto Estilo
    // @ResponseBody Envia mensagem ou variavel como retorno da requisição
    public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
    	
    	if (result.hasErrors()) {
    		return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
    	
    	try {
			estilo = cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    	
    	return ResponseEntity.ok(estilo);
    	
    }
    
}