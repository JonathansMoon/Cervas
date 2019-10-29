package com.cervas.service;

import com.cervas.model.Cerveja;
import com.cervas.repository.Cervejas;
import com.cervas.service.event.cerveja.CervejaSalvaEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCervejaService {

    @Autowired 
    private Cervejas cervejas;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    public void salvar(Cerveja cerveja) {
        cervejas.save(cerveja);

        //Este metodo ouvi o escopo e dispara um evento quando o metodo salvar é chamado
        publisher.publishEvent(new CervejaSalvaEvent(cerveja));
    }

}