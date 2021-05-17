package com.ufcg.psoft.vacineja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.repository.AdministradorRepository;
import com.ufcg.psoft.vacineja.util.Util;

@Service
public class AdministradorServiceImpl implements AdministradorService{
	
	@Autowired
	private AdministradorRepository administradorRepository;
	/*
	@Autowired
	private TipoClienteRepository tipoClienteRepository;

	@Override
	public String listarTiposDeClientes() {
		List<TipoCliente> tiposCliente = tipoClienteRepository.findAll();
		return Util.stringfyList(tiposCliente);
	}

	@Override
	public TipoCliente getTipoCliente(long tipoClienteId) {
		Optional<TipoCliente> tipoCliente = tipoClienteRepository.findById(tipoClienteId);
		return tipoCliente.isPresent() ? tipoCliente.get() : null;
	}
	
	
	*/
}
