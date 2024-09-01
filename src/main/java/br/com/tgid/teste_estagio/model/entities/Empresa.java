package br.com.tgid.teste_estagio.model.entities;


import br.com.tgid.teste_estagio.validacao.ValidadorDocumentos;

import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cnpj;

	private String nome;

	private double saldo;


	public void inicializar(String cnpj, String nome) {
		if (!ValidadorDocumentos.isValidCNPJ(cnpj)) {
			throw new IllegalArgumentException("CNPJ inválido");
		}
		this.nome = nome;
		this.cnpj = cnpj;
		this.saldo = 0.0;
	}


	public double getSaldo() {
		return saldo;
	}

	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

}

