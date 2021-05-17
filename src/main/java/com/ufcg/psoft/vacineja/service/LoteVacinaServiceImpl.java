package com.ufcg.psoft.vacineja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.LoteVacina;
import com.ufcg.psoft.vacineja.repository.LoteVacinaRepository;

@Service
public class LoteVacinaServiceImpl implements LoteVacinaService{
	@Autowired
	private LoteVacinaRepository loteVacinaRepository;
	
	@Override
	public void cadastrarLoteVacina(LoteVacina loteVacina){
		loteVacinaRepository.save(loteVacina);
	}
	
}
