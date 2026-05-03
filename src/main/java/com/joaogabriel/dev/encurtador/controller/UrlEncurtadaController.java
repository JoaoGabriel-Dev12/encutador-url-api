package com.joaogabriel.dev.encurtador.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaogabriel.dev.encurtador.dto.UrlEncurtadaDTO;
import com.joaogabriel.dev.encurtador.service.UrlEncurtadorService;

@RestController
@RequestMapping("/v1")
public class UrlEncurtadaController {

	private final UrlEncurtadorService service;

	public UrlEncurtadaController(UrlEncurtadorService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/url/shorten")
	public ResponseEntity<String> generateUrl(@RequestBody UrlEncurtadaDTO dto){
		
		if(dto.url() == null || dto.url().isBlank()) return ResponseEntity.badRequest().build();
		
		String url = service.gerarCodigo(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(url);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Void> redirect(@PathVariable String code){
		String url = service.buscarPeloCodigo(code);
		
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
	}
}
