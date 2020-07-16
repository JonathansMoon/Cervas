package com.cervas.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cervas.model.Usuario;

public class UsuariosImpl implements UsuariosQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Usuario> porEmailAtivo(String email) {
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

}
