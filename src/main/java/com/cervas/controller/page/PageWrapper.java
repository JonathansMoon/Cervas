package com.cervas.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	//	Monta as uris
	private UriComponentsBuilder uriBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}

	//	Resumindo métodos
	//	pagina.getConteudo
	//	no lugar de
	//	page.getContent
	public List<T> getConteudo() {
		return page.getContent();
	}
	
	public boolean isVazio() {
		return page.getContent().isEmpty();
	}
	
	public int getAtual() {
		return page.getNumber();
	}
	
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	//Edita a URL mantendo o filtro + o numero da pagina
	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toString();
	}
	
	//Método chamado pelo th:href da PaginaCerv
	public String urlOrdenacao(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toString());
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		//Retorna o valor do link para a ordenação 
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toString();
	}
	
	public String inverterDirecao(String propriedade) {
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		return direcao;
	}
	
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
}
