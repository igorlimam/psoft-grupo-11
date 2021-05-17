package com.ufcg.psoft.vacineja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.LoteVacina;

@Service
public interface LoteVacinaService {
	
	public void cadastrarLoteVacina(LoteVacina loteVacina);
	
	public void atualizaLoteVacina(LoteVacina loteVacina);
	
	public List<LoteVacina> getLotesVacina();

	public Optional<LoteVacina> buscaLote(long idLoteVacina);
}
