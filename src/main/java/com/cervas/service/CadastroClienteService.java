package com.cervas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cervas.model.Cliente;
import com.cervas.repository.Clientes;

@Service
public class CadastroClienteService {
	
	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

}
