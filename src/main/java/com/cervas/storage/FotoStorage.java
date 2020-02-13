package com.cervas.storage;

import org.springframework.web.multipart.MultipartFile;

// Interface a ser imprementada por FotoStorageLocal
// Se houvesse a necessidade de salva na nuvem, s3 ou etc seria necessário criar uma nova interface que o implementasse
public interface FotoStorage {

    public String salvarTemporariamente(MultipartFile[] files);

    public byte[] recuperarFotoTemporaria(String nome);
    
    public byte[] recuperarFoto(String nome);

    public void salvar(String foto);
}
