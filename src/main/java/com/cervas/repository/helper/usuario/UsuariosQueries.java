package com.cervas.repository.helper.usuario;

import java.util.Optional;

import com.cervas.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailAtivo(String email);
	
}
