package br.com.teste.gustavo.laureano.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDto {

	@NotNull(message = "Campo nome está nulo")
	@NotBlank(message = "Campo nome está vázio")
	private String nome;

	@NotNull(message = "Campo email está nulo")
	@NotBlank(message = "Campo email está vázio")
	private String email;

	@NotNull(message = "Campo telefone está nulo")
	@NotBlank(message = "Campo telefone está vázio")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private List<Long> idEnderecos = new ArrayList<Long>();
}
