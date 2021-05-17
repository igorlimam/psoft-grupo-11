package com.ufcg.psoft.vacineja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public Funcionario cadastraFuncionario(Cidadao cidadao,String localDeTrabalho, String cargo){
		
		Funcionario funcionario = new Funcionario(cidadao.getCpf());
		funcionario.setNomeCompleto(cidadao.getNomeCompleto());
		funcionario.setEndereco(cidadao.getEndereco());
		funcionario.setNumeroSUS(cidadao.getNumeroSUS());
		funcionario.setEmail(cidadao.getEmail());
		funcionario.setDataNascimento(cidadao.getDataNascimento());
		funcionario.setTelefone(cidadao.getTelefone());
		funcionario.setProfissao(cidadao.getProfissao());
		funcionario.setLocalDeTrabalho(localDeTrabalho);
		funcionario.setCargo(cargo);
		funcionarioRepository.saveAndFlush(funcionario);
		return funcionario;
	}

	@Override
	public void atualizaFuncionario(Funcionario funcionario){
		funcionarioRepository.save(funcionario);
	}
	
	@Override
	public List<Funcionario> listaFuncionarios(){
		return funcionarioRepository.findAll();
	}

	@Override
	public Optional<Funcionario> buscaFuncionario(String cpf) {
		return this.funcionarioRepository.findByCpf(cpf);
	}

	@Override
	public void aprovaFuncionario(Funcionario funcionario) {
		funcionario.setPermissao(true);
		funcionarioRepository.save(funcionario);
	}

	@Override
	public void reprovaFuncionario(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
	
}
