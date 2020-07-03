package com.cervas.repository.filter;

import com.cervas.model.Estado;

public class CidadeFilter {

	private Estado estado;
	private String nome;
	
	public CidadeFilter() {
	}
	
	public CidadeFilter(Estado estado, String nome) {
		this.estado = estado;
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
