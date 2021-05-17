package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCidadao {
	
	static final String CIDADAO_NAO_CADASTRADO = "Cidadao de cpf %s inválido";
	
	static final String CIDADAO_JA_CADASTRADO = "Cidadao de cpf %s já foi cadastrado";
	
	static final String CIDADAO_NAO_ESTA_APTO = "Cidadao de cpf %s não está apto a ser um funcionário";
	
	static final String CIDADAO_JA_ESTA_APTO = "Cidadao de cpf %s já está apto a ser um funcionário";
	
	static final String NENHUM_CIDADAO_CADASTRADO = "Nenhum cidadão foi cadastrado.";
	
	public static ResponseEntity<CustomErrorType> erroCidadaoNaoCadastrado(String cpf) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCidadao.CIDADAO_NAO_CADASTRADO, cpf)),
				HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<CustomErrorType> erroCidadaoJaCadastrado(String cpf) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCidadao.CIDADAO_JA_CADASTRADO, cpf)),
				HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<CustomErrorType> erroCidadaoNaoEstaApto(String cpf) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCidadao.CIDADAO_NAO_ESTA_APTO, cpf)),
				HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<CustomErrorType> erroCidadaoJaEstaApto(String cpf) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCidadao.CIDADAO_JA_ESTA_APTO, cpf)),
				HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<CustomErrorType> erroNenhumCidadaoCadastrado() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCidadao.NENHUM_CIDADAO_CADASTRADO)),
				HttpStatus.BAD_REQUEST);
	}
}
