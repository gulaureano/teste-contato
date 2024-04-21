package br.com.teste.gustavo.laureano.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoUpdateDto {

	private String nome;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private List<Long> idEnderecos = new ArrayList<Long>();
}
