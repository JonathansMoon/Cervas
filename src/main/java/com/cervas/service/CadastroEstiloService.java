package com.cervas.service;

import java.util.Optional;

import com.cervas.model.Estilo;
import com.cervas.repository.Estilos;
import com.cervas.service.exception.NomeEstiloJaCadastradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstiloService {

    @Autowired
    public Estilos estilos;

    @Transactional
    public Estilo salvar(Estilo estilo){
        Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
        if (estiloOptional.isPresent()) {
            /**
             * Passando a mensagem do NomeEstiloJaCadastradoException
             * para o RuntimeException para gerar a msn de erro
             *  */  
            throw new NomeEstiloJaCadastradoException("Nome do Estilo já cadastrado");
        }
        return estilos.saveAndFlush(estilo);
    }
    
}