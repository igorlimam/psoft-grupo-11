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
public class Cidadao extends Usuario {	
	
	@ElementCollection
	protected List<String> comorbidades;
	@ElementCollection
	protected List<Vacina> vacinasEstado;
	
	
	public Cidadao() {
		super();
	}
	
	public Cidadao(String cpf) {
		super(cpf);
	}
	
	public Vacina getVacinaById(long id) {
		for(Vacina vacina: vacinasEstado) {
			if(vacina.getId() == id) {
				return vacina;
			}
		}
		return null;
	}
	
	public void setVacinaEstado(Vacina vacina, int index) {
		vacinasEstado.add(index, vacina);
	}

	public int addVacina(Vacina vacina) {
		vacinasEstado.add(vacina);
		return vacinasEstado.indexOf(vacina);
	}

	public Vacina getVacina(int index) {
		return vacinasEstado.get(index);
	}

	public List<Vacina> getVacinasEstado() {
		return vacinasEstado;
	}

	public void setVacinasEstado(List<Vacina> vacinasEstado) {
		this.vacinasEstado = vacinasEstado;
	}
	
	public static void notify(List<Cidadao> cidadaos, int idadeMinima, List<String> comorbidades,
			List<String> profissoes, long diaAtual, boolean aplicaVacina) {
		for (Cidadao cidadao : cidadaos) {
			int index = 0;
			for (Vacina vacina : cidadao.getVacinasEstado()) {
				cidadao.avaliarVacina(vacina, idadeMinima, comorbidades, profissoes, diaAtual, aplicaVacina, index++);
			}
		}
	}

	public void avaliarVacina(Vacina vacina, int idadeMinima, List<String> comorbidades, List<String> profissoes,
			long diaAtual, boolean aplicaVacina, int index) {
		setVacinaEstado(vacina, index);
		boolean oldInstance = vacina instanceof NaoHabilitado;
		boolean oldInstanceSegundaDose = vacina instanceof AguardoDoseDois;
		vacina.habilitar(this, idadeMinima, comorbidades, profissoes, diaAtual, index);
		boolean newInstance = vacina instanceof VacinaDoseUm;
		boolean newInstanceDoseDois = vacina instanceof VacinaDoseDois;
		if((oldInstance && newInstance) || (oldInstanceSegundaDose && newInstanceDoseDois)) {
			System.out.println("HEY, "+getNomeCompleto()+" voce foi habilitado para tomar a dose "+ (vacina.getDosesTomadas() + 1)  +" da vacina " + vacina.getFabricante());
		}
		if (aplicaVacina) {
			vacina.vacinar(this, idadeMinima, comorbidades, profissoes, diaAtual, index);
		}
	}

	public List<String> getComorbidades() {
		return comorbidades;
	}

	public void setComorbidades(List<String> comorbidades) {
		this.comorbidades = comorbidades;
	}
	
	@Override
	public String toString() {
		return "Cidadao [cpf=" + super.cpf + ", nomeCompleto=" + nomeCompleto + ", endereco=" + endereco + ", numeroSUS="
				+ numeroSUS + ", email=" + email + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone
				+ ", profissao=" + profissao + ", comorbidades=" + comorbidades + "]";
	}

}
