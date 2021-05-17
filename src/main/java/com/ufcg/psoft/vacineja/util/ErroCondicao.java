package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroCondicao {
	
	static final String LISTA_DE_COMORBIDADES_VAZIA = "Lista de comorbidades se encontra vazia.";
	
	static final String LISTA_DE_PROFISSOES_VAZIA = "Lista de profissoes se encontra vazia.";

	static final String IDADE_INVALIDA = "Idade mininma invalida";

	public static ResponseEntity<CustomErrorType> erroListaDeComorbidadesVazia() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCondicao.LISTA_DE_COMORBIDADES_VAZIA)),
				HttpStatus.NO_CONTENT);
	}
	
	public static ResponseEntity<CustomErrorType> erroListaDeProfissoesVazia() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroCondicao.LISTA_DE_PROFISSOES_VAZIA),
				HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<?> erroIdadeMininimaInvalida(Integer idade) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroCondicao.IDADE_INVALIDA, idade)), HttpStatus.BAD_REQUEST);
	}

}