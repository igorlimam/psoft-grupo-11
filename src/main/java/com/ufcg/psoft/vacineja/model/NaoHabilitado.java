package com.ufcg.psoft.vacineja.model;

import java.util.Date;
import java.util.List;

public class NaoHabilitado extends Vacina{
	
	public NaoHabilitado() {
		super();
	}
	
	public NaoHabilitado(String fabricante, int quantidadeDoses, int diasAteSegundaDose, Date proximaDose, int dosesTomadas) {
		super(fabricante, quantidadeDoses, diasAteSegundaDose, proximaDose, dosesTomadas);
	}
	
	@Override
	public void habilitar(Cidadao cidadao, int idadeMinima, List<String> comorbidades, List<String> profissoes, long diaAtual, int index) {
		 if(isHabilitado(cidadao, idadeMinima, comorbidades, profissoes))
			 cidadao.setVacinaEstado(new VacinaDoseUm(getFabricante(), getQuantidadeDoses(), getDiasAteSegundaDose(), getProximaDose(), getDosesTomadas()), index);
	}
	
	@Override
	public String toString() {
		String txt = stringBase();
		return txt+"não habilitado";
	}
}
