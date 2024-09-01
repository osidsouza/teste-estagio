package br.com.tgid.teste_estagio.model.repositories;

import br.com.tgid.teste_estagio.model.entities.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	Empresa findByCnpj(String cnpj);
}