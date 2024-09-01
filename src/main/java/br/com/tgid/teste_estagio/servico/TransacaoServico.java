package br.com.tgid.teste_estagio.servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.tgid.teste_estagio.model.entities.Cliente;
import br.com.tgid.teste_estagio.model.entities.Empresa;
import br.com.tgid.teste_estagio.model.repositories.ClienteRepository;
import br.com.tgid.teste_estagio.model.repositories.EmpresaRepository;

@Service
public class TransacaoServico {

	private final ClienteRepository clienteRepository;
	private final EmpresaRepository empresaRepository;
	private final ServicoCallback servicoCallback;

	@Autowired
	public TransacaoServico(ClienteRepository clienteRepository, EmpresaRepository empresaRepository, ServicoCallback servicoCallback) {
		this.clienteRepository = clienteRepository;
		this.empresaRepository = empresaRepository;
		this.servicoCallback = servicoCallback;
	}

	@Transactional
	public void realizarTransacao(Long clienteId, Long empresaId, Double valor, String tipo) {
		// 1. Validar e buscar Cliente e Empresa
		Cliente cliente = buscarClientePorId(clienteId);
		Empresa empresa = buscarEmpresaPorId(empresaId);

		if (cliente == null || empresa == null) {
			throw new IllegalArgumentException("Cliente ou Empresa não encontrados.");
		}

		// 2. Verificar saldo e taxas
		double taxa = 0.0;
		if (tipo.equalsIgnoreCase("saque")) {
			taxa = calcularTaxa(valor);
			if (cliente.getSaldo() < (valor + taxa)) {
				throw new IllegalArgumentException("Saldo insuficiente.");
			}
		}

		// 3. Atualizar saldos e taxas
		boolean deposito = false;
		if (tipo.equalsIgnoreCase("deposito")) {
			cliente.setSaldo(cliente.getSaldo() + valor);
			empresa.setSaldo(empresa.getSaldo() + valor);
			deposito = true;
		} else if (tipo.equalsIgnoreCase("saque")) {
			cliente.setSaldo(cliente.getSaldo() - (valor + taxa));
			empresa.setSaldo(empresa.getSaldo() + taxa - (valor)); // A taxa vai para a empresa
		} else {
			throw new IllegalArgumentException("Tipo de transação inválido.");
		}

		// Salvar as mudanças
		clienteRepository.save(cliente);
		empresaRepository.save(empresa);

		// 4. Enviar callback e notificações
		servicoCallback.enviarNotificacao(cliente.getCpf(), valor, deposito);
		servicoCallback.enviarCallback(valor, deposito);
	}

	private Cliente buscarClientePorId(Long clienteId) {
		return clienteRepository.findById(clienteId).orElse(null);
	}

	private Empresa buscarEmpresaPorId(Long empresaId) {
		return empresaRepository.findById(empresaId).orElse(null);
	}

	private double calcularTaxa(Double valor) {
		return valor * 0.02; // taxa fixa de 2%
	}
}
