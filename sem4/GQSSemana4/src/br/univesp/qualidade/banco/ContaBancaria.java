package br.univesp.qualidade.banco;

/**
 * Representa uma conta bancaria de um sistema bancario.
 * Existem varios tipos possiveis de conta bancaria como, por exemplo,
 * conta corrente e conta investimento.
 * @author Fabio Levy Siqueira
 */
public interface ContaBancaria {
	/**
	 * Obtem o valor do saldo da conta.
	 */
	public double getSaldo();
	
	/**
	 * Retira o valor da conta corrente, decrementando o seu saldo.
	 * @param valor O valor a ser retirado.
	 * @return true se ha saldo para a retirada, ou false caso contr�rio.
	 * @throw IllegalArgumentException Caso o valor a ser retirado seja menor ou igual a 0.
	 */
	public boolean retirar(double valor);
	
	/**
	 * Deposita um valor na conta corrente.
	 * @param valor O valor a ser depositado
	 * @throw IllegalArgumentException Caso o valor a ser depositado seja menor ou igual a 0.
	 */
	public void depositar(double valor);
	
	/**
	 * Transfere o valor da conta atual para a conta destino.
	 * @param destino A conta destino, a qual tera o seu saldo incrementado com o valor.
	 * @param valor O valor a ser transferido.
	 * @return true caso tenha sido possivel transferir, ou false caso contrario.
	 * @throw IllegalArgumentException Caso o valor a ser transferido seja menor ou igual a 0.
	 */
	public boolean transferirPara(ContaBancaria destino, double valor);


}
