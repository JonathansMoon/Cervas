package com.cervas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Cerveja {

	@NotBlank(message = "SKU é Obrigatório")
	private String sku;
	
	@NotBlank(message = "Nome é Obrigatório")
	private String nome;
	
	@NotBlank(message = "Descrição é Obrigatório")
	@Size(max = 50)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
