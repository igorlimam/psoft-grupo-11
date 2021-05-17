package com.ufcg.psoft.vacineja.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.NaoHabilitado;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.model.VacinaDoseDois;
import com.ufcg.psoft.vacineja.model.VacinaDoseUm;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;

@Service
public class CidadaoServiceImpl implements CidadaoService{

	@Autowired
	private CidadaoRepository cidadaoRepository;
	
	@Override
	public String cadastrarCidadao(String nomeCompleto, String endereco, String cpf, String numeroSUS, String email,
			Date dataNascimento, String telefone, String profissao, List<String> comorbidades, List<Vacina> vacinas) {
		Cidadao cidadao = new Cidadao(cpf);
		cidadao.setNomeCompleto(nomeCompleto);
		cidadao.setEndereco(endereco);
		cidadao.setNumeroSUS(numeroSUS);
		cidadao.setEmail(email);
		cidadao.setDataNascimento(dataNascimento);
		cidadao.setTelefone(telefone);
		cidadao.setProfissao(profissao);
		cidadao.setComorbidades(comorbidades);
		Cidadao novoCidadao = cidadaoRepository.saveAndFlush(cidadao);
		for(Vacina vacina: vacinas) {
			novoCidadao.addVacina(new NaoHabilitado(vacina.getFabricante(), 
					vacina.getQuantidadeDoses(), vacina.getDiasAteSegundaDose(), 
					vacina.getProximaDose(), vacina.getDosesTomadas()));
		}
		return novoCidadao.getCpf();
	}
	
	@Override
	public String atualizarCidadao(String cpf, String nomeCompleto, String endereco, String numeroSUS, String email,
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
	
	@Override
	public Optional<Cidadao> buscaCidadao(String cpf) {
		return cidadaoRepository.findByCpf(cpf);
	}

	@Override
	public boolean agendarVacinaDose(long vacinaId, String cpf, Date data) {
		Cidadao cidadao = buscaCidadao(cpf).get();
		Vacina vacina = cidadao.getVacinaById(vacinaId);
		if(vacina instanceof VacinaDoseUm || vacina instanceof VacinaDoseDois) {
			return vacina.setProximaDoseAgendada(data);
		}
		return false;
	}

	@Override
	public List<Vacina> getVacinasCidadao(String cpf) {
		Cidadao cidadao = buscaCidadao(cpf).get();
		return cidadao.getVacinasEstado();
	}

	@Override
	public void addVacina(Vacina vacina) {
		List<Cidadao> cidadaos = cidadaoRepository.findAll();
		for(Cidadao cidadao: cidadaos) {
			cidadao.addVacina(new NaoHabilitado(vacina.getFabricante(), vacina.getQuantidadeDoses(), 
					vacina.getDiasAteSegundaDose(), vacina.getProximaDose(), 
					vacina.getDosesTomadas()));
		}
	}


}
