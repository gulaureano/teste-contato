package br.com.teste.gustavo.laureano.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.gustavo.laureano.domain.Endereco;
import br.com.teste.gustavo.laureano.dto.EnderecoAllDto;
import br.com.teste.gustavo.laureano.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;

	public List<EnderecoAllDto> findAll() {
		List<Endereco> enderecos = repository.findAll();
		return converterContatoAllDto(enderecos);
	}

	protected List<EnderecoAllDto> converterContatoAllDto(List<Endereco> enderecos) {
		List<EnderecoAllDto> enderecosDto = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			EnderecoAllDto enderecoDto = new EnderecoAllDto();
			enderecoDto.setRua(endereco.getRua());
			enderecoDto.setNumero(endereco.getNumero());
			enderecoDto.setCep(endereco.getCep());
			enderecosDto.add(enderecoDto);
		}
		return enderecosDto;
	}

	public Endereco findById(Long idEndereco) {
		return repository.getReferenceById(idEndereco);
	}

}
