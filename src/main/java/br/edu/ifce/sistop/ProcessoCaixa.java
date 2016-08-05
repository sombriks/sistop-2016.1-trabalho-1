package br.edu.ifce.sistop;

import java.sql.Timestamp;
import java.util.concurrent.Semaphore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
class ProcessoCaixa extends Thread {

  @NonNull
  private String          nome;
  @NonNull
  private AgenciaBancaria agencia;
  private ProcessoCliente clienteAtual;
  private int             atendidos = 0;
  private Semaphore       atende    = new Semaphore(1, true);

  void setClienteAtual(ProcessoCliente clienteAtual) {
    this.clienteAtual = clienteAtual;
    atende.release();// solta o caixa pra atender
  }

  void atende(ProcessoCliente cli) {
    long t1 = System.currentTimeMillis();

    String s = "O caixa [%s] iniciou o atendimento do cliente (%d) de senha %s às %s";
    s = String.format(s, nome, cli.getId(), cli.getSenha(), new Timestamp(t1));
    log.info(s);

    long t3 = cli.pagaContas(t1); // FIXME threadificar o cliente

    s = "O caixa [%s] finalizou o atendimento do cliente (%s) às %s";
    s = String.format(s, nome, clienteAtual.getId(), new Timestamp(t3));
    log.info(s);

    atendidos++;
    // dispensa o cliente
    clienteAtual = null;
    // avisa a agência que terminou o atendimento
    agencia.finalizouAtendimento(this);
  }

  @Override
  @SneakyThrows
  public void run() {
    atende.acquire();// começar fechando
    while (agencia.isAberta()) {
      atende.acquire(); // dorme até ter cliente
      atende(clienteAtual);
    }
    String s = "[%s] está indo pra casa, a agência fechou por hoje.";
    s = String.format(s, nome);
    log.info(s);
  }
}
