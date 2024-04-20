package br.com.teste.gustavo.laureano.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.gustavo.laureano.domain.Contato;
import br.com.teste.gustavo.laureano.domain.Endereco;
import br.com.teste.gustavo.laureano.dto.ContatoAllDto;
import br.com.teste.gustavo.laureano.dto.ContatoCadastradoDto;
import br.com.teste.gustavo.laureano.dto.ContatoCreateDto;
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

	public ContatoCadastradoDto create(ContatoCreateDto contatoCreate) {
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
		return converterContatoCadastradoDto(contato);
	}

	private ContatoCadastradoDto converterContatoCadastradoDto(Contato contato) {
		ContatoCadastradoDto contatoDto = new ContatoCadastradoDto();
		contatoDto.setNome(contato.getNome());
		contatoDto.setEmail(contato.getEmail());
		contatoDto.setTelefone(contato.getTelefone());
		contatoDto.setDataNascimento(contato.getDataNascimento());
		if(!contato.getEnderecos().isEmpty()) {
			for (Endereco end : contato.getEnderecos()) {
				contatoDto.getIdEnderecos().add(end.getId());
			}
		}
		contatoDto.setMensagemRetorno("Contato cadastrado com sucesso!");
		return contatoDto;
	}

}
