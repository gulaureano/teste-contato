package br.com.gustavo.laureano.teste.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroPadrao {

	private String erro;
	private String messagem;

	public ErroPadrao() {
		super();
	}

	public ErroPadrao(String erro, String messagem) {
		super();
		this.erro = erro;
		this.messagem = messagem;
	}

}
