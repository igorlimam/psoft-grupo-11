package com.ufcg.psoft.vacineja.service;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.LoteVacina;

@Service
public interface LoteVacinaService {
	public void cadastrarLoteVacina(LoteVacina loteVacina);
}
