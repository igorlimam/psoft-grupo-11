package com.ufcg.psoft.vacineja.DTO;

public class ClienteDTO {
	private long id;
	private long tipoClienteId;
	
	public ClienteDTO(long id, long tipoClienteId) {
		super();
		this.id = id;
		this.tipoClienteId = tipoClienteId;
	}
	
	public ClienteDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTipoClienteId() {
		return tipoClienteId;
	}

	public void setTipoClienteId(long tipoClienteId) {
		this.tipoClienteId = tipoClienteId;
	}
	
}
