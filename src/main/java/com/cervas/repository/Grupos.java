package com.cervas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cervas.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long>{

}
