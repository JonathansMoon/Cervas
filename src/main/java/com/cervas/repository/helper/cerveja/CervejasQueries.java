package com.cervas.repository.helper.cerveja;

import java.util.List;

import com.cervas.model.Cerveja;
import com.cervas.repository.filter.CervejaFilter;

import org.springframework.data.domain.Pageable;

public interface CervejasQueries{

    public List<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}