package com.ufcg.psoft.vacineja.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
public class Funcionario extends Usuario { 
	
	private boolean permissao = false;
	private String cargo;
	private String localDeTrabalho;
	
	public Funcionario() {
		super();
	}
	
	public Funcionario(String cpf) {
		super(cpf);
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getLocalDeTrabalho() {
		return this.localDeTrabalho;
	}

	public void setLocalDeTrabalho(String localDeTrabalho) {
		this.localDeTrabalho = localDeTrabalho;
	}

	public boolean getPermissao() {
		return this.permissao;
	}

	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}
	
}
