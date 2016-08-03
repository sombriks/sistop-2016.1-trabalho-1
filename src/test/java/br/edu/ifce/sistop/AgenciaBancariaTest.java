package br.edu.ifce.sistop;

import org.junit.Test;

public class AgenciaBancariaTest {

  @Test
  public void deveriaRodarOk() {
    AgenciaBancaria ab = new AgenciaBancaria(5);

    int i = 10;
    while (i-- > 0)
      ab.recebeCliente(i, (long) (5000 + Math.random() * 10000));

    i = 10;
    while (i-- > 0)
      ab.atendeProximoCliente();

    ab.fechaAgencia();

  }
}
