package com.ufcg.psoft.vacineja.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
 * Eu, como funcionário do governo, gostaria de cadastrar 
 * no sistema lotes de um determinado tipo de vacina cadastrada, 
 * com informações da data de validade, número de 
 * doses no lote e o tipo da vacina
 */
@Entity
public class LoteVacina {
	@Id
	@GeneratedValue
	private long id;
	private long idVacina;
	private int numeroDeDoses;
	private Date validadeDoLote;
	
	public LoteVacina() {
        this.id = 0;
    }

    public LoteVacina(long idVacina, int numeroDeDoses, Date validadeDoLote) {
        this.idVacina = idVacina;
        this.numeroDeDoses = numeroDeDoses;
        this.validadeDoLote = validadeDoLote;
    }

    public long getId() {
        return id;
    }
    
    public long getIdVacina() {
        return this.idVacina;
    }

    public int getNumeroDeDoses() {
        return this.numeroDeDoses;
    }

    public void setNumeroDeDoses(int numeroDeDoses) {
        this.numeroDeDoses = numeroDeDoses;
    }
    
    public Date getValidadeDoLote() {
        return this.validadeDoLote;
    }
}
