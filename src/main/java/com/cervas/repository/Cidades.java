package com.cervas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cervas.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Integer> {
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
}
