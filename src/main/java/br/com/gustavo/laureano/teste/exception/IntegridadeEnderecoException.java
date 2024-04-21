package br.com.gustavo.laureano.teste.exception;

public class IntegridadeEnderecoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeEnderecoException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegridadeEnderecoException(String message) {
		super(message);
	}

}
