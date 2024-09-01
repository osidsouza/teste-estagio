package br.com.tgid.teste_estagio.servico;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoCallback {

	@Value("${webhook.url}")
	private String webhookUrl; 

	private final RestTemplate restTemplate;

	public ServicoCallback(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// Método para enviar callback para a empresa
	public void enviarCallback(double valor, boolean deposito) {
		String mensagem = "Empresa: Transação " + (deposito ? "de depósito" : "de saque") + " de valor " + valor;
		enviarMensagem(mensagem);
	}

	// Método para enviar notificação para o cliente
	public void enviarNotificacao(String cpf, double valor, boolean deposito) {
		String mensagem = "Cliente " + cpf + ": " + (deposito ? "Depósito de " : "Saque de ") + valor;
		enviarMensagem(mensagem);
	}

	// Método para enviar uma mensagem para o webhook
	private void enviarMensagem(String mensagem) {
		String jsonInputString = "{ \"message\": \"" + mensagem + "\" }";
		restTemplate.postForObject(webhookUrl, jsonInputString, String.class);
	}
}
