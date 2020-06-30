package com.cervas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cervas.model.Cliente;
import com.cervas.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries{

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
