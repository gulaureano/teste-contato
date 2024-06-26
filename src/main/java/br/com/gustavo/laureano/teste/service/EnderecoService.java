package br.com.gustavo.laureano.teste.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gustavo.laureano.teste.domain.Endereco;
import br.com.gustavo.laureano.teste.dto.EnderecoAllDto;
import br.com.gustavo.laureano.teste.dto.EnderecoDto;
import br.com.gustavo.laureano.teste.dto.EnderecoUpdateDto;
import br.com.gustavo.laureano.teste.exception.EnderecoInexistenteException;
import br.com.gustavo.laureano.teste.exception.IntegridadeEnderecoException;
import br.com.gustavo.laureano.teste.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public List<EnderecoAllDto> findAll() {
		List<Endereco> enderecos = repository.findAll();
		return converterEnderecoAllDto(enderecos);
	}

	protected List<EnderecoAllDto> converterEnderecoAllDto(List<Endereco> enderecos) {
		List<EnderecoAllDto> enderecosDto = enderecos.stream().map(endereco -> {
			EnderecoAllDto enderecoDto = new EnderecoAllDto();
			enderecoDto.setRua(endereco.getRua());
			enderecoDto.setNumero(endereco.getNumero());
			enderecoDto.setCep(endereco.getCep());
			return enderecoDto;
		}).collect(Collectors.toList());
		return enderecosDto;
	}

	public Endereco findById(Long idEndereco) {
		Optional<Endereco> end = repository.findById(idEndereco);
		if (end.isPresent()) {
			return end.get();
		}
		return null;
	}

	public String create(EnderecoDto enderecoCreate) {
		Endereco endereco = new Endereco();
		endereco.setRua(enderecoCreate.getRua());
		endereco.setNumero(enderecoCreate.getNumero());
		endereco.setCep(enderecoCreate.getCep());
		repository.save(endereco);
		return "Novo endereço cadastrado com sucesso!";
	}

	public void update(EnderecoUpdateDto enderecoUpdate, Long id) {
		Endereco endereco = findById(id);
		if (endereco == null) {
			throw new EnderecoInexistenteException("Endereço Inexistente no ID: " + id);
		}
		if (enderecoUpdate.getRua() != null && !enderecoUpdate.getRua().isEmpty()
				&& !enderecoUpdate.getRua().equals(endereco.getRua())) {
			endereco.setRua(enderecoUpdate.getRua());
		}

		if (enderecoUpdate.getNumero() != null && !enderecoUpdate.getNumero().isEmpty()
				&& !enderecoUpdate.getNumero().equals(endereco.getNumero())) {
			endereco.setNumero(enderecoUpdate.getNumero());
		}

		if (enderecoUpdate.getCep() != null && !enderecoUpdate.getCep().isEmpty()
				&& !enderecoUpdate.getCep().equals(endereco.getCep())) {
			endereco.setCep(enderecoUpdate.getCep());
		}
		repository.save(endereco);
	}

	public void delete(Long id) {
		Endereco endereco = findById(id);
		if (endereco == null) {
			throw new EnderecoInexistenteException("Endereço Inexistente no ID: " + id);
		}
		try {
			repository.delete(endereco);
		} catch (Exception e) {
			throw new IntegridadeEnderecoException("O endereço: " + endereco.getRua() + ", n" + endereco.getNumero()
					+ " está sendo utilizado em algum contato, remova-o do contato para poder deletar!");
		}
	}

}
