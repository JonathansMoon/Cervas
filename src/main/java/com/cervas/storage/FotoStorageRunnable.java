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
		//Retorna o resultado para salvar a foto no banco
		//Retorna o Random + nome do aquivo para a variavel nomeFoto apos salvar temporariamente na pasta
		String nomeFoto = fotoStorage.salvarTemporariamente(files);
		String contentType = files[0].getContentType();
		//.setResult() Informa que o resultado está pronto e carrega valores
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
