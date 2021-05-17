package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroFuncionario {

	static final String FUNCIONARIO_NAO_CADASTRADO = "Funcionario de cpf %s não existe";
	static final String FUNCIONARIO_JA_CADASTRADO = "Funcionario de cpf %s já existe";
	static final String FUNCIONARIO_NAO_PERMITIDO = "Funcionario de cpf %s ainda não tem a permissão de fazer esta operação";;

	public static ResponseEntity<CustomErrorType> erroFuncionarioNaoCadastrado(String cpf) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_NAO_CADASTRADO, cpf)),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<?> erroFuncionarioJaCadastrado(String cpf) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_JA_CADASTRADO, cpf)),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<?> erroFuncionarioNaoPermitido(String cpf) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_NAO_PERMITIDO, cpf)),
				HttpStatus.BAD_REQUEST);
	}
}
