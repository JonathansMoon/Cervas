package com.cervas.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

    public String salvarTemporariamente(MultipartFile[] files);

    public byte[] recuperarFoto(String nome);

    public void salvar(String foto);
}
