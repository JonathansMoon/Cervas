package com.cervas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cervas.dto.FotoDTO;
import com.cervas.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/cervejas/fotos")
public class FotosController {

	@PostMapping
	//O MultipartFile[] recebe do parametro files[] que vem do ajax
	//Faz a postergação do resultado
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>(); 
		
		//TODO: Recebe arquivos do file e retorna resultado
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado));
		thread.start();
		
		return resultado;
	}
}
