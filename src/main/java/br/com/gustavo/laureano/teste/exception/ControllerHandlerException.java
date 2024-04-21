package br.com.gustavo.laureano.teste.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerHandlerException {

	@ExceptionHandler(ContatoInexistenteException.class)
	public ResponseEntity<ErroPadrao> contatoInexistente(ContatoInexistenteException e, HttpServletRequest request) {
		ErroPadrao error = new ErroPadrao("Contato Inexistente", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EnderecoInexistenteException.class)
	public ResponseEntity<ErroPadrao> enderecoInexistente(EnderecoInexistenteException e, HttpServletRequest request) {
		ErroPadrao error = new ErroPadrao("Endereço Inexistente", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ContatoEnderecoRepetidoException.class)
	public ResponseEntity<ErroPadrao> contatoEnderecoRepetido(ContatoEnderecoRepetidoException e,
			HttpServletRequest request) {
		ErroPadrao error = new ErroPadrao("Contato ou Endereco Repetido", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(IntegridadeEnderecoException.class)
	public ResponseEntity<ErroPadrao> integridadeException(IntegridadeEnderecoException e, HttpServletRequest request) {
		ErroPadrao error = new ErroPadrao("Endereço sendo utilizado", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> validationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ValidacaoErro error = new ValidacaoErro("Erro de Validação", "Validação Falha");
		ex.getFieldErrors().forEach(fieldError -> error.addErro(fieldError.getField(), fieldError.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
