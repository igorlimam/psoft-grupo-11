package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class VacinaDoseDois extends Vacina{
	
	public VacinaDoseDois() {
		super();
	}
	
	public VacinaDoseDois(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
		setNomeEstado("Habilitado dose dois");
	}
	
	@Override
	public void vacinar(Cidadao cidadao, int index) {
		LocalDate localDate = getProximaDose().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if((localDate.isBefore(now) || localDate.isEqual(now)) && getDosesTomadas() < 2) {
			setDosesTomadas(getDosesTomadas() + 1);
			cidadao.setVacinaEstado(new VacinaFinalizada(getFabricante(), getQuantidadeDoses(), 
					getDiasAteSegundaDose(), getProximaDose(), getDosesTomadas()), index);
		}
		
	}
	
	@Override
	public String toString() {
		return stringBase()+"Habilitado para dose dois. Sera vacinado dia "+getProximaDose();
	}
	
}
