package com.cervas.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cervas.model.Cidade;
import com.cervas.repository.filter.CidadeFilter;

public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable);
}
