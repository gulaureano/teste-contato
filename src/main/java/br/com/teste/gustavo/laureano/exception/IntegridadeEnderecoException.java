package br.com.teste.gustavo.laureano.exception;

public class IntegridadeEnderecoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeEnderecoException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegridadeEnderecoException(String message) {
		super(message);
	}

}
