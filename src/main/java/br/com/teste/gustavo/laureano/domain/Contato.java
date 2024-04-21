package br.com.teste.gustavo.laureano.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contato {

	public Contato() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	@ManyToMany
	private List<Endereco> enderecos = new ArrayList<>();

	public void addEndereco(Endereco end) {
		if (end != null) {
			this.enderecos.add(end);
		}
	}

	public void removeEndereco(Endereco end) {
		if (end != null) {
			this.enderecos.remove(end);
		}
	}

	public List<Long> getIdsEnderecos() {
		return this.enderecos.stream().map(Endereco::getId).collect(Collectors.toList());
	}

}