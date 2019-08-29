package com.cervas.service;

import com.cervas.model.Cerveja;
import com.cervas.repository.Cervejas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCervejaService {

    @Autowired 
    public Cervejas cervejas;

    @Transactional
    public void salvar(Cerveja cerveja) {
        cervejas.save(cerveja);
    }

}