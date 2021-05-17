package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vacina {
	
	@Id
	@GeneratedValue
	private long id;
	private String fabricante;
	private int quantidadeDoses;
	private int diasAteSegundaDose;
	private Date proximaDose;
	private int dosesTomadas;
	
	public Vacina() {}
	
	public Vacina(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		setFabricante(fabricante);
		setQuantidadeDoses(quantidadeDoses);
		setDiasAteSegundaDose(diasAteSegundaDose);
		setProximaDose(proximaDose);
		setDosesTomadas(dosesTomadas);
	}
	
	public boolean setProximaDoseAgendada(Date data) {
		boolean ret = true;
		if(proximaDose == null) proximaDose = data;
		else if(proximaDose.before(data)) {
			proximaDose = data;
		}else {
			ret = false;
		}
		return ret;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public long getId() {
		return id;
	}
	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}
	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}
	public int getDiasAteSegundaDose() {
		return diasAteSegundaDose;
	}
	public void setDiasAteSegundaDose(int diasAteSegundaDose) {
		this.diasAteSegundaDose = diasAteSegundaDose;
	}
	public Date getProximaDose() {
		return proximaDose;
	}
	public void setProximaDose(Date proximaDose) {
		this.proximaDose = proximaDose;
	}
	public int getDosesTomadas() {
		return dosesTomadas;
	}
	public void setDosesTomadas(int dosesTomadas) {
		this.dosesTomadas = dosesTomadas;
	}
	
	public void vacinar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {};
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {};
	
	public boolean isHabilitado(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes) {
		boolean umaPraDeusVer = getIdade(cidadao.getDataNascimento()) >= idadeMinima;
		umaPraDeusVer = umaPraDeusVer || includesComorbidade(comorbidades, cidadao.getComorbidades());
		umaPraDeusVer = umaPraDeusVer || profissoes.contains(cidadao.getProfissao());
		return umaPraDeusVer;
	}
	
	private int getIdade(Date cidadaoDataNascimento) {
		LocalDate nascimento = cidadaoDataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate atual = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int anoNascimento = nascimento.getYear();
		int anoAtual = atual.getYear();
		return anoAtual - anoNascimento;
	}
	
	private boolean includesComorbidade(List<String> comorbidadesElegiveis, List<String> comorbidadesUsuario) {
		for(String comorbidade: comorbidadesUsuario) {
			if(comorbidadesElegiveis.contains(comorbidade)) return true;
		}
		return false;
	}
	
	public String stringBase() {
		return "Vacina do "+getFabricante()+": ";
	}
	
}
