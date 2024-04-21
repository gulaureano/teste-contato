package br.com.teste.gustavo.laureano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.gustavo.laureano.domain.Endereco;
import br.com.teste.gustavo.laureano.dto.EnderecoAllDto;
import br.com.teste.gustavo.laureano.dto.EnderecoDto;
import br.com.teste.gustavo.laureano.dto.EnderecoUpdateDto;
import br.com.teste.gustavo.laureano.exception.EnderecoInexistenteException;
import br.com.teste.gustavo.laureano.exception.IntegridadeEnderecoException;
import br.com.teste.gustavo.laureano.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;

	public List<EnderecoAllDto> findAll() {
		List<Endereco> enderecos = repository.findAll();
		return converterEnderecoAllDto(enderecos);
	}

	protected List<EnderecoAllDto> converterEnderecoAllDto(List<Endereco> enderecos) {
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
		Optional<Endereco> end = repository.findById(idEndereco);
		if (end.isPresent()) {
			return end.get();
		} return null;		
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
		// TODO adicionar excecao de ConstraintViolantion ao tentar excluir endereco relacionado ao contato, alem de outras excecoes
		Endereco endereco = findById(id);
		if (endereco == null) {
			throw new EnderecoInexistenteException("Endereço Inexistente no ID: " + id);
		}
		try {
			repository.delete(endereco);
		} catch (Exception e) {
			throw new IntegridadeEnderecoException("O endereço: " + endereco.getRua() + ", n" + endereco.getNumero() + " está sendo utilizado em algum contato, remova-o do contato para poder deletar!");
		}
	}

}
