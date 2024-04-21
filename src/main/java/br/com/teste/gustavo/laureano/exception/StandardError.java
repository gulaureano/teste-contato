package br.com.teste.gustavo.laureano.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError {
	
	
	private String erro;
	private String messagem;
	
	public StandardError() {
		super();
	}

	public StandardError(String erro, String messagem) {
		super();
		this.erro = erro;		
		this.messagem = messagem;
	}

}
