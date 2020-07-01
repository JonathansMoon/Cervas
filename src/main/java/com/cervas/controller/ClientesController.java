package com.cervas.controller;

import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cervas.controller.page.PageWrapper;
import com.cervas.model.Cliente;
import com.cervas.model.TipoPessoa;
import com.cervas.repository.Clientes;
import com.cervas.repository.Estados;
import com.cervas.repository.filter.ClienteFilter;
import com.cervas.service.CadastroClienteService;
import com.cervas.service.exception.CpfCnpjClienteJaCadastradoException;

@Controller
@RequestMapping("clientes")
public class ClientesController {

    @Autowired
	private Estados estados;
    
    @Autowired
	private Clientes clientes;
    
    @Autowired
    private CadastroClienteService cadastroClienteService;
    
    
    @RequestMapping("novo")
    public ModelAndView novo(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
        mv.addObject("tiposPessoa", TipoPessoa.values());
        mv.addObject("estados", estados.findAll());
        return mv;  
    }
    
    @PostMapping("novo")
    public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
    	if (result.hasErrors()) {
    		return novo(cliente);
    	}

    	try {
    		cadastroClienteService.salvar(cliente);
		} catch (CpfCnpjClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
    	attributes.addFlashAttribute("mensagem" , "Cliente cadastrado com sucesso");
    	return new ModelAndView("redirect:/clientes/novo");
    }
    
    @GetMapping
    public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
    		@PageableDefault(size=2) Pageable pageable, HttpServletRequest httpServletRequest) {
    	
    	ModelAndView mv = new ModelAndView("cliente/PesquisaCliente");
    	
    	PageWrapper<Cliente> clienteWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable), httpServletRequest);
    	mv.addObject("pagina", clienteWrapper);
    	
    	return mv;
    }
    
}