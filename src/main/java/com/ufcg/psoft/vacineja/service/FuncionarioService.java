package com.ufcg.psoft.vacineja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;

@Service
public interface FuncionarioService {
	public Funcionario cadastraFuncionario(Cidadao cidadao, String LocaldeTrabalho, String cargo);
	
	public void atualizaFuncionario(Funcionario funcionario);
	
	public List<Funcionario> listaFuncionarios();

	public Optional<Funcionario> buscaFuncionario(String cpf);

	public void aprovaFuncionario(Funcionario funcionario);

	public void reprovaFuncionario(Funcionario funcionario);
	
}
