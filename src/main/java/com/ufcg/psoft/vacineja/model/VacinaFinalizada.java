package com.ufcg.psoft.vacineja.model;

import java.util.Date;

public class VacinaFinalizada extends Vacina{

	public VacinaFinalizada() {}
	
	public VacinaFinalizada(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
	}
	
	@Override
	public String toString() {
		String txt = stringBase();
		return txt + "Vacinacao finalizada";
	}
	
}
