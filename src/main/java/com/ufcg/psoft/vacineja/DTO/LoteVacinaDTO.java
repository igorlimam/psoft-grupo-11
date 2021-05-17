package com.ufcg.psoft.vacineja.DTO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoteVacinaDTO {
	@Id
	private long id;
	private long idVacina;
	private int numeroDeDoses;
	private Date validadeDoLote;
	private String cpfDoFuncionario;

    public LoteVacinaDTO(long idVacina, int numeroDeDoses, Date validadeDoLote) {
        this.idVacina = idVacina;
        this.numeroDeDoses = numeroDeDoses;
        this.validadeDoLote = validadeDoLote;
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

	public String getCpfDoFuncionario() {
		return cpfDoFuncionario;
	}

	public void setCpfDoFuncionario(String cpfDoFuncionario) {
		this.cpfDoFuncionario = cpfDoFuncionario;
	}
}
