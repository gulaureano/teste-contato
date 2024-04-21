package br.com.gustavo.laureano.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavo.laureano.teste.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
