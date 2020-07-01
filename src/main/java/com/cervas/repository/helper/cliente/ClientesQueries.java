package com.cervas.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cervas.model.Cliente;
import com.cervas.repository.filter.ClienteFilter;

public interface ClientesQueries {
	public Page<Cliente> filtrar(ClienteFilter parametrosDoFiltro, Pageable pageable);
}
