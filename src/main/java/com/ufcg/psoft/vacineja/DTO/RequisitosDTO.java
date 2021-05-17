package com.ufcg.psoft.vacineja.DTO;

import java.util.List;

public class RequisitosDTO {
	
	private List<String> comorbidades; 
	private List<String> profissoes; 
	private Integer idadeMinima;
	
	public RequisitosDTO(List<String> comorbidades, List<String> profissoes, Integer idadeMinima) {
		this.comorbidades = comorbidades;
		this.profissoes = profissoes; 
		this.idadeMinima = idadeMinima;
	}
	
	public List<String> getComorbidades() {
		return comorbidades;
	}
	public void setComorbidades(List<String> comorbidades) {
		this.comorbidades = comorbidades;
	}
	public List<String> getProfissoes() {
		return profissoes;
	}
	public void setProfissoes(List<String> profissoes) {
		this.profissoes = profissoes;
	}
	public Integer getIdadeMinima() {
		return idadeMinima;
	}
	public void setIdadeMinima(Integer idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

}
