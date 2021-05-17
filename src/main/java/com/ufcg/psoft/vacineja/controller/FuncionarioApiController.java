package com.ufcg.psoft.vacineja.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.DTO.LoteVacinaDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.LoteVacina;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import com.ufcg.psoft.vacineja.service.LoteVacinaService;
import com.ufcg.psoft.vacineja.service.VacinaService;
import com.ufcg.psoft.vacineja.util.ErroCidadao;
import com.ufcg.psoft.vacineja.util.ErroFuncionario;
import com.ufcg.psoft.vacineja.util.ErroVacina;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FuncionarioApiController {
	@Autowired
	CidadaoService cidadaoService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	VacinaService vacinaService;
	
	@Autowired
	LoteVacinaService loteVacinaService;
	
	@PostMapping("funcionario/")
	public ResponseEntity<?> cadastraNovoFuncionario(String cpf, String localDeTrabalho, String cargo){
	
		Optional<Cidadao> optionalCidadao = cidadaoService.buscaCidadao(cpf);
		
		if(! optionalCidadao.isPresent()) return ErroCidadao.erroCidadaoNaoCadastrado(cpf);
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.buscaFuncionario(cpf);
		
		if(optionalFuncionario.isPresent()) return ErroFuncionario.erroFuncionarioJaCadastrado(cpf);

		Funcionario funcionario = funcionarioService.cadastraFuncionario(optionalCidadao.get() , localDeTrabalho, cargo);
		
		return new ResponseEntity<String>("funcionario cadastrado: " , HttpStatus.OK);
	}
	
	@PostMapping("funcionario/loteVacinas")
	public ResponseEntity<?> cadastraNovoLoteDeVacinas(@RequestBody LoteVacinaDTO loteVacinaDTO, String cpf){
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.buscaFuncionario(cpf);
		
		if(!optionalFuncionario.isPresent()) return ErroFuncionario.erroFuncionarioNaoCadastrado(cpf);
		
		if(! optionalFuncionario.get().getPermissao()) return ErroFuncionario.erroFuncionarioNaoPermitido(cpf);
		
		Optional<Vacina> optionalVacina = vacinaService.buscarVacina(loteVacinaDTO.getIdVacina());
		
		if(!optionalVacina.isPresent()) return ErroVacina.erroVacinaNaoCadastrada(loteVacinaDTO.getIdVacina());
		
		//if(!optionalVacina.get().getAptidao()) return ErroCidadao.erroCidadaoNaoCadastrado(idVacina);
		LoteVacina novoLoteVacina = new LoteVacina(loteVacinaDTO.getIdVacina(), loteVacinaDTO.getNumeroDeDoses(), loteVacinaDTO.getValidadeDoLote());
		
		loteVacinaService.cadastrarLoteVacina(novoLoteVacina);//(optionalVacina.get(), localDeTrabalho, cargo);
		
		return new ResponseEntity<String>("funcionario cadastrado: " , HttpStatus.OK);
	}

}
