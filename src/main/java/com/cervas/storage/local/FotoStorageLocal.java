package com.cervas.storage.local;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.cervas.storage.FotoStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageLocal implements FotoStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);

    private Path local;
    private Path localTemporario;

    public FotoStorageLocal() {
        // Salva no Home e cria uma pasta
        this.local = FileSystems.getDefault().getPath(System.getenv("HOME"), ".cervasfotos");
        criarPastas();
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

    @Override
    public void salvarTemporariamente(MultipartFile[] files) {
        System.out.println("Salvando a foto Temporariamente");

    }
}