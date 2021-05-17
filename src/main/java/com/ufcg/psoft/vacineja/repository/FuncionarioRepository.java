package com.ufcg.psoft.vacineja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.vacineja.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Optional<Funcionario> findByCpf(String cpf);
	
}
