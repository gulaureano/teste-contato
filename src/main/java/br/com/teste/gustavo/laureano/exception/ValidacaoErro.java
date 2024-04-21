package br.com.teste.gustavo.laureano.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErro extends ErroPadrao {
	
	public ValidacaoErro() {
	}

	public ValidacaoErro(String erro, String messagem) {
		super(erro, messagem);
	}
	
	private List<ErroCamposException> erros = new ArrayList<>();
	
	public List<ErroCamposException> getErros(){
		return this.erros;
	}
	
	public void addErro(String campo, String mensagem) {
		this.erros.add(new ErroCamposException(campo, mensagem));
	}
	
	

}
