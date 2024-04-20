package br.com.teste.gustavo.laureano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.gustavo.laureano.dto.ContatoAllDto;
import br.com.teste.gustavo.laureano.dto.ContatoCadastradoDto;
import br.com.teste.gustavo.laureano.dto.ContatoCreateDto;
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
	public ResponseEntity<ContatoCadastradoDto> create(@RequestBody ContatoCreateDto contatoCreate){
		ContatoCadastradoDto contatoCadastrado = service.create(contatoCreate);
		return ResponseEntity.ok(contatoCadastrado);
	}

}
