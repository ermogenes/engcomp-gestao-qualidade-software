package br.univesp.qualidade.banco;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ContaCorrenteTest {
    
    private ContaCorrente conta;
    private static final int CONTA_NUMERO = 12345;
    private static final double CONTA_SALDO_INICIAL = 0.0;
    
    public ContaCorrenteTest() {
    }
    
    @Before
    public void inicio(){
        conta = new ContaCorrente(CONTA_NUMERO, CONTA_SALDO_INICIAL);
    }

    @Test
    public void saldoInicialEstaCorreto() {
        assert(conta.getSaldo() == CONTA_SALDO_INICIAL);
    }
    
    @Test
    public void saldoEstaCorretoAposDeposito() {
        double deposito = 100.10;
        conta.depositar(deposito);
        assert(conta.getSaldo() == deposito);
    }
    
    @Test
    public void saldoEstaCorretoAposRetirada() {
        double deposito = 100.10;
        double saque = 100.00;
        conta.depositar(deposito);
        conta.retirar(saque);
        assert(conta.getSaldo() == deposito - saque);
    }

    @Test
    public void saldoEstaCorretoAposTransferencia() {
        double deposito = 100.10;
        double transferido = 100.00;
        conta.depositar(deposito);
        assertTrue(
            conta.transferirPara(
                new ContaCorrenteDestinoTransferenciaStub(), transferido
            )
        );
        assert(conta.getSaldo() == deposito - transferido);
    }
    
    @Test
    public void naoPermiteTransferenciaCasoSemSaldo() {
        assertFalse(
            conta.transferirPara(
                new ContaCorrenteDestinoTransferenciaStub(), conta.getSaldo() + 0.01
            )
        );
    }
    
    @Test
    public void numeroContaEstaCorreto() {
        assert(conta.getNumero() == CONTA_NUMERO);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void naoPermiteDepositoRegativo() {
        conta.depositar(-0.01);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void naoPermiteRetiradaRegativa() {
        conta.retirar(-0.01);
    }
    
    @Test
    public void naoPermiteRetiradaCasoNaoPossuaSaldo() {
        assertFalse(conta.retirar(0.01));
        assert(conta.getSaldo() == CONTA_SALDO_INICIAL);
    }
    
    /* Stub de ContaCorrente que reporta sucesso para qualquer transferência */
    private class ContaCorrenteDestinoTransferenciaStub implements ContaBancaria {
        
        public ContaCorrenteDestinoTransferenciaStub(){
        }
        
	public int getNumero() {
            return 0;
	}

	@Override
        public double getSaldo() {
            return 0.00;
	}

	@Override
	public boolean retirar(double valor) {
            return false;
	}

	@Override
	public void depositar(double valor) {
	}

	@Override
	public boolean transferirPara(ContaBancaria destino, double valor) {
            return true;
        }
    }
}

    
