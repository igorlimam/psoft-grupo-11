package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLoteVacina {

	static final String SEM_LOTES_CADASTRADOS = "Não há lote cadastrado";
	
	public static ResponseEntity<CustomErrorType> erroSemLotesCadastrados() {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLoteVacina.SEM_LOTES_CADASTRADOS),
				HttpStatus.NOT_FOUND);
	}
}

