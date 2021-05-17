package com.ufcg.psoft.vacineja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.vacineja.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
