package com.ufcg.psoft.vacineja.DTO;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class VacinaDTO {

	private String fabricante;
	private int quantidadeDoses;
	private int diasAteSegundaDose;
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}
	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}
	public int getDiasAteSegundaDose() {
		return diasAteSegundaDose;
	}
	public void setDiasAteSegundaDose(int diasAteSegundaDose) {
		this.diasAteSegundaDose = diasAteSegundaDose;
	}
	
}
