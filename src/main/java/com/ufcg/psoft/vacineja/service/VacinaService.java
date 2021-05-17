package com.ufcg.psoft.vacineja.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.Vacina;

@Service
public interface VacinaService {
	public long cadastrarVacina(String fabricante, int quantidadeDosese, int diasAteSegundaDose);
	public List<Vacina> getAllVacinas();
	public Optional<Vacina> buscarVacina(long idVacina);
}
