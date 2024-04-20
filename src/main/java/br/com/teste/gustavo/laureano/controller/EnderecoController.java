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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.gustavo.laureano.dto.EnderecoAllDto;
import br.com.teste.gustavo.laureano.dto.EnderecoDto;
import br.com.teste.gustavo.laureano.service.EnderecoService;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<List<EnderecoAllDto>> findAll(){
		List<EnderecoAllDto> enderecos = service.findAll();
		return ResponseEntity.ok(enderecos);
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody EnderecoDto enderecoCreate){
		return ResponseEntity.ok(service.create(enderecoCreate));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody EnderecoDto enderecoUpdate, @PathVariable Long id){
		boolean alterado = service.update(enderecoUpdate, id);
		if (alterado) {
			return ResponseEntity.ok("Informações do endereco atualizadas com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o endereco com esse ID: " + id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		boolean deletado = service.delete(id);
		if (deletado) {
			return ResponseEntity.ok("endereco deletado com sucesso!");
		} return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o endereco");
	}

}
