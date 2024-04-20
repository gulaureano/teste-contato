package br.com.teste.gustavo.laureano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.gustavo.laureano.dto.EnderecoAllDto;
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

}
