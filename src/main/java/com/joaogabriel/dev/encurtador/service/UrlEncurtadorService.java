package com.joaogabriel.dev.encurtador.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joaogabriel.dev.encurtador.domain.UrlEncurtada;
import com.joaogabriel.dev.encurtador.dto.UrlEncurtadaDTO;
import com.joaogabriel.dev.encurtador.repository.UrlEncurtadaRepository;

@Service
public class UrlEncurtadorService {

	private final UrlEncurtadaRepository repo;
	
	@Value("${app.base-url}")
	private String baseUrl;

	public UrlEncurtadorService(UrlEncurtadaRepository repo) {
		super();
		this.repo = repo;
	}
	
	public String gerarCodigo(UrlEncurtadaDTO dto) {
		
		String code;
		do {
		    code = UUID.randomUUID().toString().substring(0, 6);
		} while (repo.existsByCode(code));
		
		UrlEncurtada url = new UrlEncurtada(null, code, dto.url(), OffsetDateTime.now());
		url = repo.save(url);
		
		String novaUrl = baseUrl+ "/" +url.getCode();
		return novaUrl;
	}
	
	public String buscarPeloCodigo(String code) {
		UrlEncurtada url = repo.findByCode(code).orElseThrow(() -> new RuntimeException("Url não encontrada"));
		return url.getUrlOriginal();
	}
}
