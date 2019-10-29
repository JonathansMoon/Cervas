package com.cervas.service.event.cerveja;

import com.cervas.storage.FotoStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CervejaListener{

    @Autowired
    private FotoStorage fotoStorage;

    //#evento Referencia o atributo evento de CervejaSalvaEvent
    @EventListener(condition = "#evento.temFoto()")
    public void cervejaSalva(CervejaSalvaEvent evento){
        fotoStorage.salvar(evento.getCerveja().getFoto());
    }
}