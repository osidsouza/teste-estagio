package br.com.tgid.teste_estagio.model.entities;

import org.springframework.stereotype.Component;

import br.com.tgid.teste_estagio.validacao.ValidadorDocumentos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Component
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private double saldo;

	public void inicializar(String cpf, String nome) {
		if (!ValidadorDocumentos.isValidCPF(cpf)) {
			throw new IllegalArgumentException("CPF inválido.");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.saldo = 0.0;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
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


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	} 
}
