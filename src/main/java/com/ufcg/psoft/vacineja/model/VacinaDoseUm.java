package com.ufcg.psoft.vacineja.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class VacinaDoseUm extends Vacina{
	
	public VacinaDoseUm() {
		super();
	}
	
	public VacinaDoseUm(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
		setNomeEstado("Habilitado dose um");
	}
	 
	@Override
	public void vacinar(Cidadao cidadao, int index) {
		setDosesTomadas(getDosesTomadas()+1);
		if(getDosesTomadas() >= 1) {
			if(getQuantidadeDoses() > 1) {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DATE, getDiasAteSegundaDose());
				cidadao.setVacinaEstado(new AguardoDoseDois(getFabricante(), getQuantidadeDoses(), 
						getDiasAteSegundaDose(), c.getTime(), getDosesTomadas()), 
						index);
			}else {
				cidadao.setVacinaEstado(new VacinaFinalizada(getFabricante(), getQuantidadeDoses(), 
						getDiasAteSegundaDose(), getProximaDose(), getDosesTomadas()), 
						index);
			}
			
		}
	}
	
	@Override
	public String toString() {
		String txt = stringBase();
		return txt + "Habilitado para dose um. Sera vacinado dia " + getProximaDose();
	}
	
}
