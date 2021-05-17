package com.ufcg.psoft.vacineja.DTO;

import java.util.Date;

public class AgendaDTO {

	private String cpf;
	private Date data;
	private long vacinaId;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public long getVacinaId() {
		return vacinaId;
	}
	public void setVacinaId(long vacinaId) {
		this.vacinaId = vacinaId;
	}
	
}
