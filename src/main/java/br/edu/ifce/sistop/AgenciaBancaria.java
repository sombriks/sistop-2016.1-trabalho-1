package br.edu.ifce.sistop;

import static br.edu.ifce.sistop.StatusCliente.NaFila;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaBancaria {

  private List<ProcessoCaixa>   caixas       = new ArrayList<>();
  private List<ProcessoCliente> clientes     = new ArrayList<>();
  private Semaphore             cxlivres;

  // cosmetic
  private String                nomes[]      = { "José", "Antônio", "Dorneles", "Atílio", "Fernando", "Bruce" };
  private String                sobrenomes[] = { "Silva", "Resende", "Parente", "Antunes", "Tobias", "Wayne" };
  private String                senhas[]     = { "A", "B", "C", "D", "1", "2", "3", "4", "5", "6", "7", "X", "K" };

  @Getter
  private int                   totalCaixas;
  @Getter
  private boolean               aberta;

  private int rnd(int max) {
    return (int) Math.floor(Math.random() * max);
  }

  public AgenciaBancaria(int numCaixas) {
    log.info("Abrindo a agência");
    aberta = true;
    totalCaixas = numCaixas;
    // os caixas tem nomes
    int l1 = nomes.length;
    int l2 = sobrenomes.length;
    // semáforo justo, fifo
    cxlivres = new Semaphore(numCaixas, true);
    while (numCaixas-- > 0) {
      String nome = nomes[rnd(l1)] + " " + sobrenomes[rnd(l2)];
      ProcessoCaixa cx = new ProcessoCaixa(nome, this);
      log.info("Novo caixa: " + cx);
      caixas.add(cx);
      cx.start();
    }
  }

  public void recebeCliente(long i, long tempoAtendimento) {
    int l = senhas.length;
    // já os clientes só tem senha
    String senha = senhas[rnd(l)] + senhas[rnd(l)] + senhas[rnd(l)] + senhas[rnd(l)];

    ProcessoCliente novoCliente = new ProcessoCliente(senha, i, tempoAtendimento, NaFila);
    log.info("Novo cliente: " + novoCliente);
    clientes.add(novoCliente);
  }

  @SneakyThrows
  @Synchronized("cxlivres") // mutex
  public void atendeProximoCliente() {
    cxlivres.acquire();
    ProcessoCaixa pc = caixas.remove(0);
    ProcessoCliente cli = clientes.remove(0);
    pc.setClienteAtual(cli);
    // pc.notify();
  }

  public int totalClientes() {
    return clientes.size();
  }

  public int caixasLivres() {
    return caixas.size();
  }

  void finalizouAtendimento(ProcessoCaixa caixa) {
    caixas.add(caixa);
    cxlivres.release();
  }

  @SneakyThrows
  public void fechaAgencia() {
    String s = String.format("Expulsando da agência os %s clientes restantes", clientes.size());
    log.info(s);
    clientes.removeAll(clientes);
    while (totalCaixas != caixas.size())
      Thread.sleep(1000);
    aberta = false;

    log.info("Resumo de atendimento: ");
    int i = caixas.size();
    while (i-- > 0) {
      ProcessoCaixa cx = (ProcessoCaixa) caixas.get(i);
      s = "%s atendeu %s clientes";
      s = String.format(s, cx.getNome(), cx.getAtendidos());
      log.info(s);
    }
  }
}
