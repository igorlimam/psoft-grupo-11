package com.ufcg.psoft.vacineja.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Vacina;

public interface CidadaoService {
	public String cadastrarCidadao(String nomeCompleto, String endereco, String cpf, String numeroSUS, 
			String email, Date dataNascimento, String telefone, String profissao, List<String> comorbidades, List<Vacina> vacinas);
	public String atualizarCidadao(String cpf, String nomeCompleto, String endereco, String numeroSUS, String email, 
			Date dataNascimento, String telefone, String profissao, List<String> comorbidades);
	public Optional<Cidadao> buscaCidadao(String cpf);
	public boolean agendarVacinaDose(long vacinaId, String cpf, Date data);
	public List<Vacina> getVacinasCidadao(String cpf);
	public void addVacina(Vacina vacina);
	public void notificar(int idadeMinima, List<String> comorbidades, List<String> profissoes);
	public void vacinar(String cpf, long vacinaId);
	public List<Cidadao> getCidadaos();
	public String consultarEstadoVacinas(String cpf);

}
