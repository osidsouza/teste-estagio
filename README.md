# Documentação do Sistema

## Introdução
Este documento descreve o sistema desenvolvido para gerenciar transações financeiras entre duas entidades principais: **Empresas** e **Clientes**. O sistema foi construído utilizando **Spring Boot**, **JPA**, e **Java**.

## Entidades Principais

### 1. Cliente
- **CPF**: Cada Cliente possui um CPF único que é validado pelo sistema.
- **Operações**: Os Clientes podem realizar depósitos e saques através das Empresas cadastradas no sistema.

### 2. Empresa
- **CNPJ**: Cada Empresa possui um CNPJ único que é validado pelo sistema.
- **Saldo**: As Empresas possuem um saldo que é afetado pelas transações realizadas por seus Clientes, considerando a dedução das taxas de administração.
- **Taxas**: Cada Empresa deve ter ao menos um tipo de taxa que será aplicada em cada transação (seja saque ou depósito).

## Funcionalidades

### 1. Validação de CPF e CNPJ
- O sistema valida automaticamente os CPFs dos Clientes e os CNPJs das Empresas durante o cadastro. Entradas inválidas serão rejeitadas.
- exemplo de erro
- ![image](https://github.com/user-attachments/assets/82b14d99-8fa5-4522-8756-1b76e54e045c)
exemplo de sucesso
![image](https://github.com/user-attachments/assets/0db22f35-5b62-4c9c-bb2e-57e567235985)


### 2. Gestão de Saldo das Empresas
- Cada Empresa possui um saldo que reflete as operações financeiras realizadas pelos Clientes. As transações impactam diretamente o saldo da Empresa, considerando as taxas administrativas.
  - **Depósito**: O Cliente deposita dinheiro na conta da Empresa, aumentando o saldo da mesma e com taxa isenta
  - **Saque**: O Cliente retira dinheiro da conta da Empresa, reduzindo o saldo disponível e a taxa de 2%.

### 3. Transações e Taxas
- No momento de uma transação de saque o uma taxa é aplicada, e o saldo é atualizado de acordo com o valor da transação menos a taxa.
- No momento de uma transação de deposito a taxa é isenta.

- 
- exemplo de falta de saldo
- ![image](https://github.com/user-attachments/assets/ce81f053-806d-4646-a68f-e0f6f3e7ed1e)


exemplo de saque bem sucedido
![image](https://github.com/user-attachments/assets/8daf4e2f-a67e-4a7a-befc-ca4eede5f130)


exemplo de deposito bem sucedido
![image](https://github.com/user-attachments/assets/d5e01240-e87e-44bc-b6f7-04d6fc08835e)

### 4. Notificações e Callback
- **Callback para Empresa**: Ao realizar uma transação, o sistema envia um callback para a Empresa, informando sobre a transação realizada simulado pelo webhook

- exemplo de callback
-  ![image](https://github.com/user-attachments/assets/b949a0cf-56ff-4482-981c-0db7e6330443)

- **Notificações para Cliente**: Após a conclusão de uma transação, o Cliente recebe uma notificação simulada pelo webhook.site
- exemplo de notificação

- ![image](https://github.com/user-attachments/assets/e0858034-e9d7-463b-8cc8-5416a1f4a73b)


## Fluxo de Operações

### 1. Cadastro
- A Empresa e o Cliente são cadastrados no sistema com seus respectivos CNPJ e CPF, que são validados no momento do cadastro.

### 2. Transações
- pode ser testada pelo postma, passando um requisição post conforme print

- ![image](https://github.com/user-attachments/assets/66caaaee-34ec-4df9-a5e8-f1e8978c670d)


## Tecnologias Utilizadas

- **Spring Boot**: Framework para criação do backend do sistema.
- **JPA (Java Persistence API)**: Utilizado para gerenciamento de dados persistentes.

exemplo dos dados persistidos

![image](https://github.com/user-attachments/assets/965122ab-b851-4ef9-85a9-06a6c174bbfb)


![image](https://github.com/user-attachments/assets/4c31aebf-72e6-4434-83f7-99a65d9d8fb7)

- **Java**: Linguagem de programação principal do sistema.
- **Webhook.site**: Ferramenta para simulação de callbacks.

## Considerações Finais
Este sistema foi projetado para ser modular e escalável, permitindo a inclusão de novas funcionalidades e a integração com outros sistemas. Ele também visa garantir a segurança e a integridade dos dados através de validações rigorosas de CPF e CNPJ, bem como a correta aplicação de taxas e atualizações de saldo.
