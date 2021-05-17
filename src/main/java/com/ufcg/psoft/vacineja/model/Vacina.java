package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Vacina {
	
	@Id
	@GeneratedValue
	private long id;
	private String fabricante;
	private int quantidadeDoses;
	private int diasAteSegundaDose;
	private Date proximaDose;
	private int dosesTomadas;
	private String nomeEstado = "Vacina";
	
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

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
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
	
	public void vacinar(Cidadao cidadao, int index) {};
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, int index) {};
	
	public boolean isHabilitado(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes) {
		boolean habilitado = getIdade(cidadao.getDataNascimento()) >= idadeMinima;
		habilitado = habilitado || includesComorbidade(comorbidades, cidadao.getComorbidades());
		habilitado = habilitado || profissoes.contains(cidadao.getProfissao());
		return habilitado;
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
