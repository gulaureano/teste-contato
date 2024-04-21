package br.com.teste.gustavo.laureano.exception;

public class ContatoInexistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContatoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContatoInexistenteException(String message) {
		super(message);
	}
}