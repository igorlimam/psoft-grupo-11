package com.ufcg.psoft.vacineja.model;

import java.util.Date;
import java.util.List;

public class AguardoDoseDois extends Vacina{

	public AguardoDoseDois() {
		super();
	}
	
	public AguardoDoseDois(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
	}
	
	@Override
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes,
			long diaAtual, int index) {
		cidadao.setVacinaEstado(new VacinaDoseDois(getFabricante(), getQuantidadeDoses(), 
				getDiasAteSegundaDose(), getProximaDose(), getDosesTomadas()), index);
	}
	
	@Override
	public String toString() {
		return stringBase()+"Aguardando habilitacao para segunda dose";
	}
	
}
