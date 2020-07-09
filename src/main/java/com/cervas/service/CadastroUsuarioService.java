package com.cervas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cervas.model.Usuario;
import com.cervas.repository.Usuarios;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;

	@Transactional
	public void salvar(Usuario usuario) {
		try {
			usuarios.save(usuario);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
