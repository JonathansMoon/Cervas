package com.cervas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cervejas/fotos")
public class FotosController {

	@PostMapping
	//O MultipartFile[] recebe o arquivo files[] que vem do ajax
	public String upload(@RequestParam("files[]") MultipartFile[] files) {
		System.out.println("files: " + files.length);
		return "Ok!";
	}
}
