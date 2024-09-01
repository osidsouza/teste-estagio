package br.com.tgid.teste_estagio.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tgid.teste_estagio.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByCpf(String cpf);
}