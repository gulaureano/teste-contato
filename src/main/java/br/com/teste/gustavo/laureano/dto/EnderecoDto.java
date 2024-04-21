package br.com.teste.gustavo.laureano.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

	@NotNull(message = "Campo rua está nulo")
	@NotBlank(message = "Campo rua está vázio")
	private String rua;

	@NotNull(message = "Campo numermo está nulo")
	@NotBlank(message = "Campo numero está vázio")
	private String numero;

	@NotNull(message = "Campo cep está nulo")
	@NotBlank(message = "Campo cep está vázio")
	private String cep;

}
