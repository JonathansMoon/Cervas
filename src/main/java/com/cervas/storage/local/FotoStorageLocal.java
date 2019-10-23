package com.cervas.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import com.cervas.storage.FotoStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoStorageLocal implements FotoStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

    private Path local;
    private Path localTemporario;

    public FotoStorageLocal() {
        // Salva no Home e cria uma pasta
        this.local = FileSystems.getDefault().getPath(System.getProperty("user.home"), ".cervasfotos");
        criarPastas();
    }

    @Override
    public String salvarTemporariamente(MultipartFile[] files) {
        String novoNome = null;
        if (files != null && files.length > 0) {
            MultipartFile arquivo = files[0];
            novoNome = renomearArquivo(arquivo.getOriginalFilename());
            try {
                arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString()
                        + FileSystems.getDefault().getSeparator() + novoNome));
            } catch (Exception e) {
                throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
            }
        }
        return novoNome;

    }

    private void criarPastas() {
        try {
            Files.createDirectories(this.local);
            this.localTemporario = FileSystems.getDefault().getPath(this.local.toString(), "temp");
            Files.createDirectories(this.localTemporario);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Pastas criadas com sucessso");
                LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
                LOGGER.debug("Pasta Temporária: " + this.localTemporario.toAbsolutePath());

            }

        } catch (IOException e) {
            throw new RuntimeException("Erro criando pasta para salvar foto", e);
        }
    }

    private String renomearArquivo(String nomeOriginal) {
        String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Nome original: %s Novo nome do arquivo %s", nomeOriginal, novoNome));
        }
        return novoNome;
    }

    @Override
    public byte[] recuperarFotoTemporaria(String nome) {    
        try {        
            System.out.println(this.localTemporario.resolve(nome));
            return Files.readAllBytes(this.localTemporario.resolve(nome));
        } catch (Exception e) {
            throw new RuntimeException("Erro lendo a foto temporária!");
        }
    }
}