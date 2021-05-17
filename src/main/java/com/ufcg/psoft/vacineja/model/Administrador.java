package com.ufcg.psoft.vacineja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrador {

	@Id
	@GeneratedValue
	private long id;
	
	public Administrador(long id) {
		this.id = id;
	}
	
	public Administrador() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
