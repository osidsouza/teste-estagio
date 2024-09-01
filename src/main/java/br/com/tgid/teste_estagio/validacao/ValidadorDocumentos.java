package br.com.tgid.teste_estagio.validacao;



public class ValidadorDocumentos {

	public static boolean isValidCPF(String cpf) {
		// Remove caracteres não numéricos
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifica se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os dígitos são iguais (caso de CPF inválido comum)
		if (cpf.chars().distinct().count() == 1) {
			return false;
		}

		// Valida o primeiro dígito verificador
		int soma = 0;
		int peso = 10;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * peso--;
		}
		int primeiroDigitoVerificador = calcularDigitoVerificador(soma);

		// Valida o segundo dígito verificador
		soma = 0;
		peso = 11;
		for (int i = 0; i < 9; i++) {
			soma += (cpf.charAt(i) - '0') * peso--;
		}
		soma += primeiroDigitoVerificador * 2;
		int segundoDigitoVerificador = calcularDigitoVerificador(soma);

		// Compara os dígitos verificadores calculados com os fornecidos
		return cpf.charAt(9) == (char) (primeiroDigitoVerificador + '0') &&
				cpf.charAt(10) == (char) (segundoDigitoVerificador + '0');
	}

	// Método auxiliar para calcular o dígito verificador
	private static int calcularDigitoVerificador(int soma) {
		int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;
	}


	public static boolean isValidCNPJ(String cnpj) {
		cnpj = cnpj.replaceAll("[^0-9]", "");

		if (cnpj.length() != 14) {
			return false;
		}

		if (cnpj.chars().distinct().count() == 1) {
			return false;
		}

		int soma = 0;
		int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		for (int i = 0; i < 12; i++) {
			soma += (cnpj.charAt(i) - '0') * pesosPrimeiroDigito[i];
		}
		int primeiroDigitoVerificador = calcularDigitoVerificadorCNPJ(soma);

		soma = 0;
		int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		for (int i = 0; i < 12; i++) {
			soma += (cnpj.charAt(i) - '0') * pesosSegundoDigito[i];
		}
		soma += primeiroDigitoVerificador * 2;
		int segundoDigitoVerificador = calcularDigitoVerificadorCNPJ(soma);

		return cnpj.charAt(12) == (char) (primeiroDigitoVerificador + '0') &&
				cnpj.charAt(13) == (char) (segundoDigitoVerificador + '0');
	}


	// Método auxiliar para calcular o dígito verificador do CNPJ
	private static int calcularDigitoVerificadorCNPJ(int soma) {
		int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;
	}
}
