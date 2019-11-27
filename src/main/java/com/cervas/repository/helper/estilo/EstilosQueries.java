package com.cervas.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cervas.model.Estilo;
import com.cervas.repository.filter.EstiloFilter;

public interface EstilosQueries {
	public Page<Estilo> filtrar(EstiloFilter parametrosDoFiltro, Pageable pageable);
}
