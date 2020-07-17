package com.cervas.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.cervas.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
}
