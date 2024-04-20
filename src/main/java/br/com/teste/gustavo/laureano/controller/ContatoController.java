package br.com.teste.gustavo.laureano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.teste.gustavo.laureano.dto.ContatoAllDto;
import br.com.teste.gustavo.laureano.dto.ContatoDto;
import br.com.teste.gustavo.laureano.dto.ContatoEnderecoDto;
import br.com.teste.gustavo.laureano.service.ContatoService;

@RequestMapping("/contato")
@RestController
public class ContatoController {
	
	@Autowired
	private ContatoService service;
	
	@GetMapping
	public ResponseEntity<List<ContatoAllDto>> findAll(){
		List<ContatoAllDto> contatos = service.findAll();
		return ResponseEntity.ok(contatos);
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody ContatoDto contatoCreate){
		return ResponseEntity.ok(service.create(contatoCreate));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody ContatoDto contatoUpdate, @PathVariable Long id){
		boolean alterado = service.update(contatoUpdate, id);
		if (alterado) {
			return ResponseEntity.ok("Informações do contato atualizadas com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o contato com esse ID: " + id);
	}
	
	@PatchMapping("/removeEndereco")
	public ResponseEntity<String> deleteEndereco(@RequestBody ContatoEnderecoDto contatoRemove){
		boolean removido = service.removeEndereco(contatoRemove);
		if (removido) {
			return ResponseEntity.ok("Endereço removido do contato com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o contato ou o endereço");
	}
	
	@PutMapping("/addEndereco")
	public ResponseEntity<String> addEndereco(@RequestBody ContatoEnderecoDto contatoAdd){
		boolean adicionado = service.adicionaEndereco(contatoAdd);
		if (adicionado) {
			return ResponseEntity.ok("Endereço adicionado ao contato com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o contato ou o endereço");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		boolean deletado = service.delete(id);
		if (deletado) {
			return ResponseEntity.ok("Contato deletado com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o contato");
	}

}
