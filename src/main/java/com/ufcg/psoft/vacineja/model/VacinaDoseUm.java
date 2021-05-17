package com.ufcg.psoft.vacineja.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VacinaDoseUm extends Vacina{
	
	public VacinaDoseUm() {
		super();
	}
	
	public VacinaDoseUm(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
	}
	 
	@Override
	public void vacinar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {
		setDosesTomadas(getDosesTomadas()+1);
		this.habilitar(cidadao, idadeMinima, comorbidades, profissoes, diaAtual, index);
	}
	
	@Override
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {
		if(getDosesTomadas() >= 1 && getQuantidadeDoses() > 1) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, getDiasAteSegundaDose());
			cidadao.setVacinaEstado(new AguardoDoseDois(getFabricante(), getQuantidadeDoses(), 
					getDiasAteSegundaDose(), c.getTime(), getDosesTomadas()), 
					index);
		}
	}
	
	@Override
	public String toString() {
		String txt = stringBase();
		return txt + "Habilitado para dose um. Sera vacinado dia " + getProximaDose();
	}
	
}
