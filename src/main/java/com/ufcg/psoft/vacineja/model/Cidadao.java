package com.ufcg.psoft.vacineja.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cidadao extends Usuario {	
	
	@ElementCollection
	protected List<String> comorbidades;
	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	protected List<Vacina> vacinasEstado;
	
	
	public Cidadao() {
		super();
	}
	
	public Cidadao(String cpf) {
		super(cpf);
	}
	
	private void checkEstados() {
		if(vacinasEstado == null) vacinasEstado = new ArrayList<>();
	}
	
	public Vacina getVacinaById(long id) {
		checkEstados();
		for(Vacina vacina: vacinasEstado) {
			if(vacina.getId() == id) {
				return vacina;
			}
		}
		return null;
	}
	
	public void setVacinaEstado(Vacina vacina, int index) {
		checkEstados();
		vacinasEstado.add(index, vacina);
	}

	public int addVacina(Vacina vacina) {
		checkEstados();
		vacinasEstado.add(vacina);
		return vacinasEstado.indexOf(vacina);
	}

	public Vacina getVacina(int index) {
		checkEstados();
		return vacinasEstado.get(index);
	}

	public int getIndex(Vacina vacina) {
		checkEstados();
		return vacinasEstado.indexOf(vacina);
	}
	
	public List<Vacina> getVacinasEstado() {
		checkEstados();
		return vacinasEstado;
	}

	public void setVacinasEstado(List<Vacina> vacinasEstado) {
		checkEstados();
		this.vacinasEstado = vacinasEstado;
	}

	public void avaliarVacina(Vacina vacina, int idadeMinima, List<String> comorbidades, List<String> profissoes, int index) {
		setVacinaEstado(vacina, index);
		boolean oldInstance = vacina instanceof NaoHabilitado;
		boolean oldInstanceSegundaDose = vacina instanceof AguardoDoseDois;
		vacina.habilitar(this, idadeMinima, comorbidades, profissoes, index);
		boolean newInstance = vacina instanceof VacinaDoseUm;
		boolean newInstanceDoseDois = vacina instanceof VacinaDoseDois;
		if((oldInstance && newInstance) || (oldInstanceSegundaDose && newInstanceDoseDois)) {
			System.out.println("HEY, "+getNomeCompleto() + " de cpf " + getCpf()+
					" voce foi habilitado para tomar a dose "+ (vacina.getDosesTomadas() + 1)  +" da vacina " + vacina.getFabricante());
		}
	}

	public void aplicarVacina(int index) {
		getVacina(index).vacinar(this, index);
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
