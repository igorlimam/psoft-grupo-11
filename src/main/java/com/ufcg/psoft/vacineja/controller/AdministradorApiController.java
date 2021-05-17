package com.ufcg.psoft.vacineja.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.DTO.CidadaoDTO;
import com.ufcg.psoft.vacineja.DTO.ClienteDTO;
import com.ufcg.psoft.vacineja.DTO.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.service.AdministradorService;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import com.ufcg.psoft.vacineja.service.VacinaService;
import com.ufcg.psoft.vacineja.util.ErroCidadao;
import com.ufcg.psoft.vacineja.util.ErroFuncionario;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdministradorApiController {

	@Autowired
	AdministradorService administradorService;

	@Autowired
	CidadaoService cidadaoService;

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	VacinaService vacinaService;

	@PutMapping("aprova-funcionario/{cpf}")
	public ResponseEntity<?> aprovaFuncionario(@PathVariable String cpf) {

		Optional<Funcionario> optionalFuncionario = funcionarioService.buscaFuncionario(cpf);

		if (!optionalFuncionario.isPresent())
			return ErroFuncionario.erroFuncionarioNaoCadastrado(cpf);

		funcionarioService.aprovaFuncionario(optionalFuncionario.get());

		return new ResponseEntity<String>("cidadao " + cpf + " apto como funcionário", HttpStatus.OK);
	}

	@DeleteMapping("reprova-funcionario/{cpf}")
	public ResponseEntity<?> reprovaFuncionario(@PathVariable String cpf) {

		Optional<Funcionario> optionalFuncionario = funcionarioService.buscaFuncionario(cpf);

		if (!optionalFuncionario.isPresent())
			return ErroFuncionario.erroFuncionarioNaoCadastrado(cpf);
		
		funcionarioService.reprovaFuncionario(optionalFuncionario.get());

		return new ResponseEntity<String>("cidadao " + cpf + " foi retirado da lista de funcionarios", HttpStatus.OK);
	}
	
	@PostMapping("vacina/")
	public ResponseEntity<?> criaVacina(@RequestBody VacinaDTO vacinaDTO) {

		long idVacina = vacinaService.cadastrarVacina(vacinaDTO.getFabricante(), vacinaDTO.getQuantidadeDoses(),
				vacinaDTO.getDiasAteSegundaDose());
		cidadaoService.addVacina(vacinaService.buscarVacina(idVacina).get());
		return new ResponseEntity<String>("Vacina de ID " + idVacina + "Criada", HttpStatus.OK);
	}
	
}
