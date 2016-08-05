package br.edu.ifce.sistop;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
public class ProcessoCliente {

  private String        senha;
  private long          id;
  private long          tempoAtendimento;
  private StatusCliente status;

  /**
   * cliente pagando as contas
   * 
   * @param t1
   *          tempo em que ele começou a pagar as contas
   * @return tempo em que ele terminou de pagar as contas
   */
  long pagaContas(long t1) {
    status = StatusCliente.EmAtendimento;
    boolean sorteandoboleto = true;
    long t2 = tempoAtendimento + t1;
    long t3 = 0;
    String s = "O cliente (%s) de senha %s entrou em atendimento em %s";
    s = String.format(s, id, senha, new Timestamp(t1));
    log.info(s);
    while (sorteandoboleto) {
      // pagando boleto, falando mal do governo, etc...
      t3 = System.currentTimeMillis();
      if (t2 <= t3)
        sorteandoboleto = false;
    }
    status = StatusCliente.Atendido;
    s = "O cliente %s de senha %s finalizou atendimento em %s";
    s = String.format(s, id, senha, new Timestamp(t3));
    log.info(s);
    return t3;
  }
}
