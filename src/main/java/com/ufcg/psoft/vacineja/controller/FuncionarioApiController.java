package com.ufcg.psoft.vacineja.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.vacineja.DTO.CidadaoVacinadoDTO;
import com.ufcg.psoft.vacineja.DTO.LoteVacinaDTO;
import com.ufcg.psoft.vacineja.DTO.RequisitosDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.LoteVacina;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.service.CidadaoService;
import com.ufcg.psoft.vacineja.service.FuncionarioService;
import com.ufcg.psoft.vacineja.service.LoteVacinaService;
import com.ufcg.psoft.vacineja.service.VacinaService;
import com.ufcg.psoft.vacineja.util.ErroCidadao;
import com.ufcg.psoft.vacineja.util.ErroCondicao;
import com.ufcg.psoft.vacineja.util.ErroFuncionario;
import com.ufcg.psoft.vacineja.util.ErroLoteVacina;
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
	public ResponseEntity<?> cadastraLoteVacina(@RequestBody LoteVacinaDTO loteVacinaDTO){
		
		Optional<Funcionario> optionalFuncionario = funcionarioService.buscaFuncionario(loteVacinaDTO.getCpfDoFuncionario());
		
		if(!optionalFuncionario.isPresent()) return ErroFuncionario.erroFuncionarioNaoCadastrado(loteVacinaDTO.getCpfDoFuncionario());
		
		if(! optionalFuncionario.get().getPermissao()) return ErroFuncionario.erroFuncionarioNaoPermitido(loteVacinaDTO.getCpfDoFuncionario());
		
		Optional<Vacina> optionalVacina = vacinaService.buscarVacina(loteVacinaDTO.getIdVacina());
		
		if(!optionalVacina.isPresent()) return ErroVacina.erroVacinaNaoCadastrada(loteVacinaDTO.getIdVacina());
		
		LoteVacina novoLoteVacina = new LoteVacina(loteVacinaDTO.getIdVacina(), loteVacinaDTO.getNumeroDeDoses(), loteVacinaDTO.getValidadeDoLote());
		
		loteVacinaService.cadastrarLoteVacina(novoLoteVacina);
		
		return new ResponseEntity<String>("Lote cadastrado" , HttpStatus.OK);
	}
	
	@PutMapping("funcionario/habilitaVacinacao")
	public ResponseEntity<?> habilitaVacinacao(@RequestBody RequisitosDTO requisitos) {
		
		if(requisitos.getComorbidades().isEmpty()) return ErroCondicao.erroListaDeComorbidadesVazia();
		
		if(requisitos.getProfissoes().isEmpty()) return ErroCondicao.erroListaDeProfissoesVazia();
		
		if(requisitos.getIdadeMinima() <= 0 || requisitos.getIdadeMinima() == null) return ErroCondicao.erroIdadeMininimaInvalida(requisitos.getIdadeMinima());
		
		cidadaoService.notificar(requisitos.getIdadeMinima(), requisitos.getComorbidades(), requisitos.getProfissoes());
		
		return new ResponseEntity<String>("Grupo habilitado para a vacinação", HttpStatus.OK);
	}
	
	@PutMapping("funcionario/registraVacinacao/")
	public ResponseEntity<?> registraVacinacao(@RequestBody CidadaoVacinadoDTO cidadaoVacinado) {
		
		Optional<Cidadao> possivelCidadao = cidadaoService.buscaCidadao(cidadaoVacinado.getCpf());
		if(!possivelCidadao.isPresent()) return ErroCidadao.erroCidadaoNaoCadastrado(cidadaoVacinado.getCpf());
		
		Optional<LoteVacina> possivelLoteVacina = loteVacinaService.buscaLote(cidadaoVacinado.getIdLoteVacina());
		if(!possivelLoteVacina.isPresent()) return ErroLoteVacina.erroSemLotesCadastrados();
		
		Optional<Vacina> possivelVacina = vacinaService.buscarVacina(cidadaoVacinado.getIdVacina());
		if(!possivelVacina.isPresent()) ErroVacina.erroVacinaNaoCadastrada(cidadaoVacinado.getIdVacina());
		
		LoteVacina loteAtualizado = possivelLoteVacina.get();
		loteAtualizado.setNumeroDeDoses(loteAtualizado.getNumeroDeDoses() -1);
		loteVacinaService.atualizaLoteVacina(loteAtualizado);
		
		cidadaoService.vacinar(cidadaoVacinado.getCpf(), cidadaoVacinado.getIdVacina());
		return new ResponseEntity<String>("Cidadão portador do cpf: " + cidadaoVacinado.getCpf() + " vacinado", HttpStatus.OK);
	}
	
	@GetMapping("funcionario/estadosVacinas")
	public ResponseEntity<?> getEstadosVacinas(String cpf) {
		String estadoVacinas = cidadaoService.consultarEstadoVacinas(cpf);
		
		if(estadoVacinas == null || estadoVacinas.equals("")) return ErroVacina.erroEstadosInexistentes();
		
		return new ResponseEntity<String>(estadoVacinas, HttpStatus.OK);
	}
	
	@GetMapping("funcionario/loteVacinas")
	public ResponseEntity<?> listarVacinas() {
		List<LoteVacina> listaVacinas = loteVacinaService.getLotesVacina();
		
		if(listaVacinas.isEmpty()) return ErroLoteVacina.erroSemLotesCadastrados();
		
		return new ResponseEntity<List<LoteVacina>>(listaVacinas, HttpStatus.OK);
	}

}
