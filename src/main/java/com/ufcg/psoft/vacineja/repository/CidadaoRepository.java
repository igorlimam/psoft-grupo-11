package com.ufcg.psoft.vacineja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.vacineja.model.Cidadao;


public interface CidadaoRepository extends JpaRepository<Cidadao, Long>{
	
	Optional<Cidadao> findByCpf(String cpf);
	
}
