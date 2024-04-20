package br.com.teste.gustavo.laureano.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContatoAllDto {
	
	private String nome;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private List<EnderecoAllDto> enderecos;

}
