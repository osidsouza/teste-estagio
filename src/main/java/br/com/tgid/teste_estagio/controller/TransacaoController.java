package br.com.tgid.teste_estagio.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.tgid.teste_estagio.servico.TransacaoServico;



@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private final TransacaoServico transacaoServico;

	@Autowired
	public TransacaoController(TransacaoServico transacaoServico) {
		this.transacaoServico = transacaoServico;
	}

	// para criar uma nova transação (depósito ou saque)
	@PostMapping
	public ResponseEntity<String> realizarTransacao(
			@RequestParam Long clienteId,
			@RequestParam Long empresaId,
			@RequestParam Double valor,
			@RequestParam String tipo) {
		try {
			if (!tipo.equalsIgnoreCase("deposito") && !tipo.equalsIgnoreCase("saque")) {
				return ResponseEntity.badRequest().body("Tipo de transação inválido. Deve ser 'deposito' ou 'saque'.");
			}

			transacaoServico.realizarTransacao(clienteId, empresaId, valor, tipo);
			return ResponseEntity.ok("Transação de " + tipo + " realizada com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}