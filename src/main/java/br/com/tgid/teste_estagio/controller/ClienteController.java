package br.com.tgid.teste_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgid.teste_estagio.model.entities.Cliente;
import br.com.tgid.teste_estagio.model.repositories.ClienteRepository;
import br.com.tgid.teste_estagio.servico.ServicoCallback;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;
	private final ServicoCallback servicoCallback;

	@Autowired
	public ClienteController(ClienteRepository clienteRepository, ServicoCallback servicoCallback) {
		this.clienteRepository = clienteRepository;
		this.servicoCallback = servicoCallback;
	}

	@PostMapping
	public ResponseEntity<Cliente> criarCliente(
			@RequestParam String nome,
			@RequestParam String cpf,
			@RequestParam double saldo) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setSaldo(saldo);;


		Cliente novoCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(novoCliente);
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> encontrarClientePorCpf(@PathVariable String cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/notificacao")
	public ResponseEntity<String> realizarNotificacao(
			@RequestParam String cpf,
			@RequestParam double valor,
			@RequestParam boolean deposito) {
		servicoCallback.enviarNotificacao(cpf, valor, deposito);
		return ResponseEntity.ok("Notificação enviada com sucesso.");
	}
}