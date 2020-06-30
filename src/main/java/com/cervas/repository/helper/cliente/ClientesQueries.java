package com.cervas.repository.helper.cliente;

import org.springframework.data.domain.Pageable;
import java.util.List;

import com.cervas.model.Cliente;
import com.cervas.repository.filter.ClienteFilter;

public interface ClientesQueries {

	public List<Cliente> filter(ClienteFilter filtro, Pageable pageable);
}
