package com.cervas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cervas.controller.page.PageWrapper;
import com.cervas.model.Cidade;
import com.cervas.repository.Cidades;
import com.cervas.repository.Estados;
import com.cervas.repository.filter.CidadeFilter;
import com.cervas.service.CadastroCidadeService;
import com.cervas.service.exception.EstadoJaPossuiCidadeCadastradaException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private Estados estados;

	@Autowired
	private Cidades cidades;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/nova")
	public ModelAndView nova(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidade");
		mv.addObject("estados", estados.findAll());
		return mv;
	}

	@PostMapping("/nova")
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return nova(cidade);
		}

		try {
			cadastroCidadeService.salvar(cidade);
		} catch (EstadoJaPossuiCidadeCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Cidade criada com sucesso!");
		return new ModelAndView("redirect:/cidades/nova");
	}

	@GetMapping()
	public ModelAndView pesquisar(CidadeFilter cidadeFilter, BindingResult result,
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("cidade/PesquisaCidade");
		mv.addObject("estados", estados.findAll());
		
		PageWrapper<Cidade> cidadeWrapper = new PageWrapper<>(cidades.filtrar(cidadeFilter, pageable), httpServletRequest);
		mv.addObject("pagina", cidadeWrapper);
				
		return mv;
	}

	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return cidades.findByEstadoCodigo(codigoEstado);
	}
}
