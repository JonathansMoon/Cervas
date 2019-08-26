package com.cervas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cervas.model.Estilo;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>{

}
