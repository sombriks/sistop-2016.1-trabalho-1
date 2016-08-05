package br.edu.ifce.sistop;

import org.junit.Test;

public class AgenciaBancariaTest {

  @Test
  public void deveriaRodarOkParaUmClienteUmCaixa() {

    AgenciaBancaria ab = new AgenciaBancaria(1);

    ab.recebeCliente(1, 3000);

    ab.atendeProximoCliente();

    ab.fechaAgencia();

  }

  @Test
  public void deveriaRodarOkPara10Clientes3Caixas() {

    AgenciaBancaria ab = new AgenciaBancaria(3);

    ab.recebeCliente(0, 6000);
    ab.recebeCliente(1, 1000);
    ab.recebeCliente(2, 3000);
    ab.recebeCliente(3, 5000);
    ab.recebeCliente(4, 1000);
    ab.recebeCliente(5, 3000);
    ab.recebeCliente(6, 5000);
    ab.recebeCliente(7, 1000);
    ab.recebeCliente(8, 3000);
    ab.recebeCliente(9, 5000);

    int i = 10;
    while (i-- > 0)
      ab.atendeProximoCliente();

    ab.fechaAgencia();

  }
}
