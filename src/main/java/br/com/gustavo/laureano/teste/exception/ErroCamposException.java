package br.com.gustavo.laureano.teste.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroCamposException {
	
	private String campo;
	private String mensagemErro;
	
	public ErroCamposException(String campo, String mensagemErro) {
		super();
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}
	
}
