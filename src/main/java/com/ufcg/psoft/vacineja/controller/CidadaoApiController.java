package com.ufcg.psoft.vacineja.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.DTO.AgendaDTO;
import com.ufcg.psoft.vacineja.DTO.CidadaoDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.VacinaService;
import com.ufcg.psoft.vacineja.util.ErroCidadao;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CidadaoApiController {

	@Autowired
	CidadaoService cidadaoService;
	
	@Autowired
	VacinaService vacinaService;
	
	@RequestMapping(value="cidadao/", method=RequestMethod.POST)
	public ResponseEntity<?> cadastrarNovoCidadao(@RequestBody CidadaoDTO cidadao){
		Optional<Cidadao> optionalCidadao = cidadaoService.buscaCidadao(cidadao.getCpf());
		if(optionalCidadao.isPresent()) {
			return ErroCidadao.erroCidadaoJaCadastrado(cidadao.getCpf());
		}
		String cidadaoCpf = cidadaoService.cadastrarCidadao(cidadao.getNomeCompleto(), cidadao.getEndereco(), cidadao.getCpf(),
				cidadao.getNumeroSUS(), cidadao.getEmail(), cidadao.getDataNascimento(), 
				cidadao.getTelefone(), cidadao.getProfissao(), cidadao.getComorbidades(), vacinaService.getAllVacinas());
		return new ResponseEntity<String>("cidadao " + cidadaoCpf + " cadastrado", HttpStatus.OK);
	}
	
	@RequestMapping(value="cidadao/", method=RequestMethod.PUT)
	public ResponseEntity<?> atualizarCidadao(@RequestBody CidadaoDTO cidadao){
		Optional<Cidadao> optionalCidadao = cidadaoService.buscaCidadao(cidadao.getCpf());
		
		if(!optionalCidadao.isPresent()) {
			return ErroCidadao.erroCidadaoNaoCadastrado(cidadao.getCpf());
		}
		
		String cidadaoCpf = cidadaoService.atualizarCidadao(cidadao.getCpf(), cidadao.getNomeCompleto(), cidadao.getEndereco(),
				cidadao.getNumeroSUS(), cidadao.getEmail(), cidadao.getDataNascimento(), cidadao.getTelefone(),
				cidadao.getProfissao(), cidadao.getComorbidades());
		
		return new ResponseEntity<String>("Cidadão " + cidadaoCpf + " atualizado", HttpStatus.OK);
	}
	
	@RequestMapping(value="cidadao/agenda", method=RequestMethod.POST)
	public ResponseEntity<?> agendarVacina(@RequestBody AgendaDTO agenda){
		Optional<Cidadao> optionalCidadao = cidadaoService.buscaCidadao(agenda.getCpf());
		
		if(!optionalCidadao.isPresent()) {
			return ErroCidadao.erroCidadaoNaoCadastrado(agenda.getCpf());
		}
		
		boolean agendado = cidadaoService.agendarVacinaDose(agenda.getVacinaId(), agenda.getCpf(), agenda.getData());
		
		return new ResponseEntity<String>(agendado?"agendado":"não foi possivel agendar", HttpStatus.OK);
	}

	@RequestMapping(value="cidadao/agenda/{cpf}", method=RequestMethod.GET)
	public ResponseEntity<?> listarVacinas(@PathVariable String cpf){
		List<Vacina> vacinas = cidadaoService.getVacinasCidadao(cpf);
		return new ResponseEntity<List<Vacina>>(vacinas, HttpStatus.OK);
	}
	
}
