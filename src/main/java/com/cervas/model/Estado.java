package com.cervas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer codigo;
	@NotBlank(message = "Nome é obrigatório")
	String nome;
	@NotBlank(message = "Sigla é obrigatória")
	String sigla;
	@OneToMany(mappedBy = "estado")
	List<Cidade> cidade;
	
	public Estado() {
		
	}

	public Estado(Integer codigo, String nome, String sigla, List<Cidade> cidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
		this.cidade = cidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public List<Cidade> getCidade() {
		return cidade;
	}


}