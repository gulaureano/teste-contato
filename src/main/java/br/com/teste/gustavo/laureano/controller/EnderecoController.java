package br.com.teste.gustavo.laureano.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.teste.gustavo.laureano.dto.EnderecoUpdateDto;
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
	public ResponseEntity<String> create(@RequestBody @Valid EnderecoDto enderecoCreate){
		return ResponseEntity.ok(service.create(enderecoCreate));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody EnderecoUpdateDto enderecoUpdate, @PathVariable Long id){
		service.update(enderecoUpdate, id);
		return ResponseEntity.ok("Informações do endereco atualizadas com sucesso!");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok("Endereco deletado com sucesso!");
	}

}
