package br.com.teste.gustavo.laureano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.gustavo.laureano.domain.Contato;
import br.com.teste.gustavo.laureano.domain.Endereco;
import br.com.teste.gustavo.laureano.dto.ContatoAllDto;
import br.com.teste.gustavo.laureano.dto.ContatoDto;
import br.com.teste.gustavo.laureano.dto.ContatoEnderecoDto;
import br.com.teste.gustavo.laureano.repository.ContatoRepository;

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
		List<ContatoAllDto> contatosDto = new ArrayList<>();
		for (Contato contato : contatos) {
			ContatoAllDto contatoDto = new ContatoAllDto();
			contatoDto.setNome(contato.getNome());
			contatoDto.setEmail(contato.getEmail());
			contatoDto.setTelefone(contato.getTelefone());
			contatoDto.setDataNascimento(contato.getDataNascimento());
			contatoDto.setEnderecos(enderecoService.converterContatoAllDto(contato.getEnderecos()));
			contatosDto.add(contatoDto);
		}
		return contatosDto;
	}

	public String create(ContatoDto contatoCreate) {
		Contato contato = new Contato();
		contato.setNome(contatoCreate.getNome());
		contato.setEmail(contatoCreate.getEmail());
		contato.setDataNascimento(contatoCreate.getDataNascimento());
		contato.setTelefone(contatoCreate.getTelefone());

		if (contatoCreate.getIdEnderecos() != null) {
			for (Long id : contatoCreate.getIdEnderecos()) {
				Endereco end = enderecoService.findById(id);
				contato.addEndereco(end);
			}
		}

		repository.save(contato);
		return "Contato: " + contato.getNome() + " cadastrado com sucesso!";
	}

	public boolean update(ContatoDto contatoUpdate, Long id) {
		Optional<Contato> contato = repository.findById(id);
		if (contato.isPresent()) {
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
			return true;
		}
		return false;
	}

	public boolean adicionaEndereco(ContatoEnderecoDto contatoEndereco) {
		Endereco endereco = enderecoService.findById(contatoEndereco.getIdEndereco());
		Optional<Contato> contato = repository.findById(contatoEndereco.getId());
		if (endereco != null && contato.isPresent()) {
			if (!contato.get().getIdsEnderecos().contains(contatoEndereco.getIdEndereco())) {
				contato.get().addEndereco(endereco);
				repository.save(contato.get());
				return true;
			}
			
			return false;
		}
		return false;
	}

	public boolean removeEndereco(ContatoEnderecoDto contatoEndereco) {
		Endereco endereco = enderecoService.findById(contatoEndereco.getIdEndereco());
		Optional<Contato> contato = repository.findById(contatoEndereco.getId());
		if (endereco != null && contato.isPresent()) {
			if (!contato.get().getEnderecos().isEmpty()
					&& contato.get().getIdsEnderecos().contains(contatoEndereco.getIdEndereco())) {

				contato.get().removeEndereco(endereco);
				repository.save(contato.get());
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean delete(Long id) {
		Optional<Contato> contato = repository.findById(id); 
		if (contato.isPresent()) {
			repository.delete(contato.get());
			return true;
		}
		return false;
	}

}
