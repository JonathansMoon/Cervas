package com.cervas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cervas.model.Cidade;
import com.cervas.repository.Cidades;

@Service
public class CadastroCidadeService {
	
	@Autowired
	Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		
		
		
	}

}
