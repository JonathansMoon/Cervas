package com.cervas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cervas.model.Cidade;
import com.cervas.repository.Cidades;
import com.cervas.service.exception.EstadoJaPossuiCidadeCadastradaException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		
		Optional<Cidade> cidadeExists = cidades.findByEstadoAndNome(cidade.getEstado(), cidade.getNome());

		if (cidadeExists.isPresent()) {
			throw new EstadoJaPossuiCidadeCadastradaException("Este estado já possui a cidade informada!");
		}
		
		cidades.save(cidade);
	}

}
