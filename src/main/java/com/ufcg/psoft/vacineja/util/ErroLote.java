package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLote {

	static final String SEM_LOTES_CADASTRADOS = "Não há lote cadastrado para esse produto";
	
	static final String LOTE_INDISPONIVEL = "Não é possível conceder essa quantia desse lote";
	
	public static ResponseEntity<CustomErrorType> erroSemLotesCadastrados() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLote.SEM_LOTES_CADASTRADOS),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroLoteIndisponivel() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLote.LOTE_INDISPONIVEL),
				HttpStatus.NOT_FOUND);
	}
}

