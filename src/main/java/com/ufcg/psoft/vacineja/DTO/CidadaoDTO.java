package com.ufcg.psoft.vacineja.DTO;

import java.util.Date;
import java.util.List;

public class CidadaoDTO {

	private String cpf;
	private String nomeCompleto;
	private String endereco;
	private String numeroSUS;
	private String email;
	private Date dataNascimento;
	private String telefone;
	private String profissao;
	private List<String> comorbidades;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<String> getComorbidades() {
		return comorbidades;
	}
	public void setComorbidades(List<String> comorbidades) {
		this.comorbidades = comorbidades;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroSUS() {
		return numeroSUS;
	}
	public void setNumeroSUS(String numeroSUS) {
		this.numeroSUS = numeroSUS;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
}
