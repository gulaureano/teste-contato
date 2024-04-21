package br.com.gustavo.laureano.teste.exception;

public class EnderecoInexistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnderecoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnderecoInexistenteException(String message) {
		super(message);
	}

}
