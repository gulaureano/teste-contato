package br.com.gustavo.laureano.teste.exception;

public class ContatoEnderecoRepetidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContatoEnderecoRepetidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContatoEnderecoRepetidoException(String message) {
		super(message);
	}

}
