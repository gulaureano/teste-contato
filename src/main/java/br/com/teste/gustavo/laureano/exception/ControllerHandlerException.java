package br.com.teste.gustavo.laureano.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerHandlerException {
	
	@ExceptionHandler(ContatoInexistenteException.class)
	public ResponseEntity<StandardError> contatoInexistente(ContatoInexistenteException e, HttpServletRequest request){
		StandardError error = new StandardError("Contato Inexistente", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(EnderecoInexistenteException.class)
	public ResponseEntity<StandardError> enderecoInexistente(EnderecoInexistenteException e, HttpServletRequest request){
		StandardError error = new StandardError("Endereço Inexistente", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ContatoEnderecoRepetidoException.class)
	public ResponseEntity<StandardError> contatoEnderecoRepetido(ContatoEnderecoRepetidoException e, HttpServletRequest request){
		StandardError error = new StandardError("Contato ou Endereco Repetido", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	

}
