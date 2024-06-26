package br.com.gustavo.laureano.teste.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gustavo.laureano.teste.domain.Contato;
import br.com.gustavo.laureano.teste.domain.Endereco;
import br.com.gustavo.laureano.teste.dto.ContatoAllDto;
import br.com.gustavo.laureano.teste.dto.ContatoDto;
import br.com.gustavo.laureano.teste.dto.ContatoEnderecoDto;
import br.com.gustavo.laureano.teste.dto.ContatoUpdateDto;
import br.com.gustavo.laureano.teste.exception.ContatoEnderecoRepetidoException;
import br.com.gustavo.laureano.teste.exception.ContatoInexistenteException;
import br.com.gustavo.laureano.teste.exception.EnderecoInexistenteException;
import br.com.gustavo.laureano.teste.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;

	@Autowired
	private EnderecoService enderecoService;

	public List<ContatoAllDto> findAll() {
		List<Contato> contatos = repository.findAll();
		return converterContatoAllDto(contatos);
	}

	protected List<ContatoAllDto> converterContatoAllDto(List<Contato> contatos) {
		List<ContatoAllDto> contatosDto = contatos.stream().map(contato -> {
			ContatoAllDto contatoDto = new ContatoAllDto();
			contatoDto.setNome(contato.getNome());
			contatoDto.setEmail(contato.getEmail());
			contatoDto.setTelefone(contato.getTelefone());
			contatoDto.setDataNascimento(contato.getDataNascimento());
			contatoDto.setEnderecos(enderecoService.converterEnderecoAllDto(contato.getEnderecos()));
			return contatoDto;
		}).collect(Collectors.toList());

		return contatosDto;
	}

	public String create(ContatoDto contatoCreate) {
		Contato contato = new Contato();
		contato.setNome(contatoCreate.getNome());
		contato.setEmail(contatoCreate.getEmail());
		contato.setDataNascimento(contatoCreate.getDataNascimento());
		contato.setTelefone(contatoCreate.getTelefone());

		if (contatoCreate.getIdEnderecos() != null) {
			contatoCreate.getIdEnderecos().forEach(id -> {
				Endereco end = enderecoService.findById(id);
				contato.addEndereco(end);
			});
		}
		repository.save(contato);
		return "Contato: " + contato.getNome() + " cadastrado com sucesso!";
	}

	public void update(ContatoUpdateDto contatoUpdate, Long id) throws ContatoInexistenteException {
		if (id == null) {
			throw new NullPointerException("ID passado está vázio");
		}
		Optional<Contato> contato = repository.findById(id);
		if (!contato.isPresent()) {
			throw new ContatoInexistenteException("Não foi localizado contato com ID: " + id);
		}
		if (contatoUpdate.getNome() != null && !contatoUpdate.getNome().isEmpty()
				&& !contatoUpdate.getNome().equals(contato.get().getNome())) {
			contato.get().setNome(contatoUpdate.getNome());
		}

		if (contatoUpdate.getEmail() != null && !contatoUpdate.getEmail().isEmpty()
				&& !contatoUpdate.getEmail().equals(contato.get().getEmail())) {
			contato.get().setEmail(contatoUpdate.getEmail());
		}

		if (contatoUpdate.getTelefone() != null && !contatoUpdate.getTelefone().isEmpty()
				&& !contatoUpdate.getTelefone().equals(contato.get().getTelefone())) {
			contato.get().setTelefone(contatoUpdate.getTelefone());
		}

		if (contatoUpdate.getDataNascimento() != null
				&& !contatoUpdate.getDataNascimento().equals(contato.get().getDataNascimento())) {
			contato.get().setDataNascimento(contatoUpdate.getDataNascimento());
		}

		repository.save(contato.get());
	}

	public void adicionaEndereco(ContatoEnderecoDto contatoEndereco) {
		Endereco endereco = enderecoService.findById(contatoEndereco.getIdEndereco());
		if (endereco == null) {
			throw new EnderecoInexistenteException(
					"Não foi localizado contato com ID: " + contatoEndereco.getIdEndereco());
		}
		Optional<Contato> contato = repository.findById(contatoEndereco.getId());
		if (!contato.isPresent()) {
			throw new ContatoInexistenteException("Não foi localizado contato com ID: " + contatoEndereco.getId());
		}
		if (!contato.get().getIdsEnderecos().contains(contatoEndereco.getIdEndereco())) {
			contato.get().addEndereco(endereco);
			repository.save(contato.get());
			return;
		}
		throw new ContatoEnderecoRepetidoException("O contato: " + contato.get().getNome()
				+ " já possui este endereço: " + endereco.getRua() + " n" + endereco.getNumero());
	}

	public void removeEndereco(ContatoEnderecoDto contatoEndereco) {
		Endereco endereco = enderecoService.findById(contatoEndereco.getIdEndereco());
		if (endereco == null) {
			throw new EnderecoInexistenteException(
					"Não foi localizado endereço com ID: " + contatoEndereco.getIdEndereco());
		}
		Optional<Contato> contato = repository.findById(contatoEndereco.getId());
		if (!contato.isPresent()) {
			throw new ContatoInexistenteException("Não foi localizado contato com ID: " + contatoEndereco.getId());
		}

		if (!contato.get().getEnderecos().isEmpty()
				&& contato.get().getIdsEnderecos().contains(contatoEndereco.getIdEndereco())) {

			contato.get().removeEndereco(endereco);
			repository.save(contato.get());
			return;
		}
		throw new EnderecoInexistenteException("Contato não possui endereço ou não foi encontrado esse endereço");
	}

	public void delete(Long id) {
		Optional<Contato> contato = repository.findById(id);
		if (contato.isPresent()) {
			repository.delete(contato.get());
			return;
		}
		throw new ContatoInexistenteException("Não foi localizado contato com ID: " + id);
	}

}
