package br.com.teste.gustavo.laureano.exception;

public class EnderecoInexistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnderecoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnderecoInexistenteException(String message) {
		super(message);
	}

}
