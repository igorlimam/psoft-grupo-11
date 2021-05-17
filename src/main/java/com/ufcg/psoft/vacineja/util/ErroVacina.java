package com.ufcg.psoft.vacineja.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroVacina {
	static final String VACINA_NAO_ENCONTRADA = "Não há vacina cadastrada com esse id";
	static final String ESTADOS_NAO_ENCONTRADOS = "Estados não foram encontrados";
	
	public static ResponseEntity<CustomErrorType> erroVacinaNaoCadastrada(long idVacina) {		
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroVacina.VACINA_NAO_ENCONTRADA, idVacina)),
				HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<CustomErrorType> erroEstadosInexistentes() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroVacina.ESTADOS_NAO_ENCONTRADOS)),
				HttpStatus.BAD_REQUEST);
	}
}
