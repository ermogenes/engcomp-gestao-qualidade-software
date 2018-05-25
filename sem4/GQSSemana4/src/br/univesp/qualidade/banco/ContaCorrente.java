package br.univesp.qualidade.banco;

public class ContaCorrente implements ContaBancaria {
	private double saldo = 0;
	private int numero;

	public ContaCorrente(int numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public int getNumero() {
		return numero;
	}

	public boolean retirar(double valor) {
		if (valor <= 0) throw new IllegalArgumentException("Valor negativo");
		if (valor > saldo) return false;
		saldo -= valor;
		return true;
	}

	public void depositar(double valor) {
		if (valor <= 0) throw new IllegalArgumentException("Valor negativo");
		saldo += valor;
	}

	public boolean transferirPara(ContaBancaria destino, double valor) {
		if (this.retirar(valor)) {
			destino.depositar(valor);
			return true;
		} else return false;
	}
}