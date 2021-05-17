package com.ufcg.psoft.vacineja.DTO;

public class CidadaoVacinadoDTO {
	
	private String cpf; 
	private long idLoteVacina;
	private long idVacina;
	
	public CidadaoVacinadoDTO(String cpf, long idLoteVacina, long idVacina){
		this.cpf = cpf;
		this.idLoteVacina = idLoteVacina;
		this.idVacina = idVacina;
	}
	public String getCpf() {
		return cpf;
	}

	public long getIdLoteVacina() {
		return idLoteVacina;
	}

	public long getIdVacina() {
		return idVacina;
	}
}
