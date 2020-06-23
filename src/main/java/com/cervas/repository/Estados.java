package com.cervas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cervas.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Integer> {

    public Optional<Estado> findByNomeIgnoreCase(String nome);

}