package br.com.gustavo.laureano.teste.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavo.laureano.teste.dto.ContatoAllDto;
import br.com.gustavo.laureano.teste.dto.ContatoDto;
import br.com.gustavo.laureano.teste.dto.ContatoEnderecoDto;
import br.com.gustavo.laureano.teste.dto.ContatoUpdateDto;
import br.com.gustavo.laureano.teste.service.ContatoService;

@RequestMapping("/contato")
@RestController
public class ContatoController {

	@Autowired
	private ContatoService service;

	@GetMapping
	public ResponseEntity<List<ContatoAllDto>> findAll() {
		List<ContatoAllDto> contatos = service.findAll();
		return ResponseEntity.ok(contatos);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid ContatoDto contatoCreate) {
		return ResponseEntity.ok(service.create(contatoCreate));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody ContatoUpdateDto contatoUpdate, @PathVariable Long id) {
		service.update(contatoUpdate, id);
		return ResponseEntity.ok("Informações do contato atualizadas com sucesso!");
	}

	@PatchMapping("/removeEndereco")
	public ResponseEntity<String> deleteEndereco(@RequestBody ContatoEnderecoDto contatoRemove) {
		service.removeEndereco(contatoRemove);
		return ResponseEntity.ok("Endereço removido do contato com sucesso!");
	}

	@PutMapping("/addEndereco")
	public ResponseEntity<String> addEndereco(@RequestBody ContatoEnderecoDto contatoAdd) {
		service.adicionaEndereco(contatoAdd);
		return ResponseEntity.ok("Endereço adicionado ao contato com sucesso!");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok("Contato deletado com sucesso!");
	}

}