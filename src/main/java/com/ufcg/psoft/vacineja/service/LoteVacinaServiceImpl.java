package com.ufcg.psoft.vacineja.service;


import java.util.List;
import java.util.Optional;

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
	
	@Override
	public List<LoteVacina> getLotesVacina(){
		return loteVacinaRepository.findAll();
	}

	@Override
	public Optional<LoteVacina> buscaLote(long idLoteVacina) {
		return loteVacinaRepository.findById(idLoteVacina);
	}
	/*
	 * public String atualizarCidadao(String cpf, String nomeCompleto, String endereco, String numeroSUS, String email,
			Date dataNascimento, String telefone, String profissao, List<String> comorbidades) {
		
		Cidadao cidadao = buscaCidadao(cpf).get();
		if(nomeCompleto != null) cidadao.setNomeCompleto(nomeCompleto);
		if(endereco != null) cidadao.setEndereco(endereco);
		if(numeroSUS != null) cidadao.setNumeroSUS(numeroSUS);
		if(email != null) cidadao.setEmail(email);
		if(dataNascimento != null) cidadao.setDataNascimento(dataNascimento);
		if(telefone != null) cidadao.setTelefone(telefone);
		if(profissao != null) cidadao.setProfissao(profissao);
		if(comorbidades != null) cidadao.setComorbidades(comorbidades);
		Cidadao cidadaoAtualizado = cidadaoRepository.save(cidadao);
		return cidadaoAtualizado.getCpf();
	}
	 */
	
	@Override
	public void atualizaLoteVacina(LoteVacina loteVacina) {
		loteVacinaRepository.save(loteVacina);
	}
	
}
