package com.cervas.repository;

import org.springframework.stereotype.Repository;

import com.cervas.model.Cerveja;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long> {

}
