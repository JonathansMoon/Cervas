package com.cervas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.cervas.dto.FotoDTO;
import com.cervas.storage.FotoStorage;
import com.cervas.storage.FotoStorageRunnable;
   
@RestController
@RequestMapping("/cervejas/fotos")
public class FotosController {

	@Autowired
	private FotoStorage fotoStorage;
	
	//O MultipartFile[] recebe do parametro files[] que vem do ajax
	//Faz a postergação do resultado
	/**
	 *O DeferredResult diz para a thread que recebeu a requisição que ela está livre
	 *para receber outras requisições enquanto o metodo está produzindo o resultado
	 *O DefferredResult avisa quando o resultado do tipo FotoDTO está pronto
	 *pra ser retornado, isso através do .setResult()
	 */
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>(); 
		
		//Recebe arquivos do file e retorna resultado
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
		thread.start();
		
		return resultado;
	}

	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		return fotoStorage.recuperarFotoTemporaria(nome);
	}
}
