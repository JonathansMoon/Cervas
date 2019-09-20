package com.cervas.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cervas.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;


	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}


	@Override
	public void run() {

		//Salava a foto no sistema de arquivos
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		//.setResult() Informa que o resultado está pronto e carrega valores
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
