package com.cervas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer codigo;
    @NotBlank(message = "Nome é obrigatório")
    String nome;
    @ManyToOne
    @JoinColumn(name ="codigo_estado")
    Estado estado;
    
    public Cidade() {
    	
    }

    public Cidade(Integer codigo, String nome, Estado estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Estado getestado() {
        return estado;
    }
}