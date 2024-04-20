package br.com.teste.gustavo.laureano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.gustavo.laureano.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
