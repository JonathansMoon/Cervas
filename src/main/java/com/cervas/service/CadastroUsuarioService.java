package com.cervas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cervas.model.Usuario;
import com.cervas.repository.Usuarios;
import com.cervas.service.exception.EmailJaCadastradoException;
import com.cervas.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Usuarios usuarios;

	@Transactional
	public void salvar(Usuario usuario) {

		Optional<Usuario> emailExists = usuarios.findByEmail(usuario.getEmail());
		
		if (emailExists.isPresent()) {
			throw new EmailJaCadastradoException("Email já cadastrado!");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para usuário!");
		}	
		
		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
	}
}
