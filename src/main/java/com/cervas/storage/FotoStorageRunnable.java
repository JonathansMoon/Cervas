package com.cervas.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cervas.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;


	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		this.files 			= files;
		this.resultado 		= resultado;
		this.fotoStorage 	= fotoStorage;
	}


	@Override
	public void run() {

		fotoStorage.salvarTemporariamente(files);
		//Salva a foto no sistema de arquivos
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		//.setResult() Informa que o resultado está pronto e carrega valores
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
