package com.cervas.repository.helper.cerveja;

import com.cervas.model.Cerveja;
import com.cervas.repository.filter.CervejaFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CervejasQueries{

    public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}