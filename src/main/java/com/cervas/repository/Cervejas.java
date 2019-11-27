package com.cervas.repository;

import org.springframework.stereotype.Repository;

import com.cervas.model.Cerveja;
import com.cervas.repository.helper.cerveja.CervejasQueries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {

	public Optional<Cerveja> findBySku(String sku);
	public Optional<Cerveja> findByNome(String nome);
}
