package com.joaogabriel.dev.encurtador.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaogabriel.dev.encurtador.domain.UrlEncurtada;

@Repository
public interface UrlEncurtadaRepository extends JpaRepository<UrlEncurtada, UUID> {

	Optional<UrlEncurtada> findByCode(String code);
	boolean existsByCode(String code);
}
