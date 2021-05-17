package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class VacinaDoseDois extends Vacina{
	
	public VacinaDoseDois() {
		super();
	}
	
	public VacinaDoseDois(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
	}
	
	@Override
	public void vacinar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {
		setDosesTomadas(getDosesTomadas() + 1);
		cidadao.setVacinaEstado(new VacinaFinalizada(getFabricante(), getQuantidadeDoses(), 
				getDiasAteSegundaDose(), getProximaDose(), getDosesTomadas()), index);
	}
	
	@Override
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {
		LocalDate localDate = getProximaDose().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if((localDate.isBefore(now) || localDate.isEqual(now)) && getDosesTomadas() < 2)
			this.vacinar(cidadao, idadeMinima, comorbidades, profissoes, diaAtual, index);
	}
	
	@Override
	public String toString() {
		return stringBase()+"Habilitado para dose dois. Sera vacinado dia "+getProximaDose();
	}
	
}
