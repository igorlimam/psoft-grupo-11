package com.ufcg.psoft.vacineja.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;

@Service
public class VacinaServiceImpl implements VacinaService{
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Override
	public Optional<Vacina> buscarVacina(long idVacina) {
		return vacinaRepository.findById(idVacina);
	}

	@Override
	public long cadastrarVacina(String fabricante, int quantidadeDosese, int diasAteSegundaDose) {
		Vacina vacina = new Vacina();
		vacina.setFabricante(fabricante);
		vacina.setQuantidadeDoses(quantidadeDosese);
		vacina.setDiasAteSegundaDose(diasAteSegundaDose);
		vacinaRepository.save(vacina);
		return vacina.getId();
	}

	@Override
	public List<Vacina> getAllVacinas() {
		return vacinaRepository.findAll();
	}
}
