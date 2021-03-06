package com.cervas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.cervas.model.Estilo;
import com.cervas.repository.helper.estilo.EstilosQueries;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{

    public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
