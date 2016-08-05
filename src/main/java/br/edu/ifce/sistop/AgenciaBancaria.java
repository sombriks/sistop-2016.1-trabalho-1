package br.edu.ifce.sistop;

import static br.edu.ifce.sistop.StatusCliente.NaFila;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaBancaria {

  @Getter
  private List<ProcessoCaixa>   caixas       = new ArrayList<>();
  @Getter
  private List<ProcessoCliente> clientes     = new ArrayList<>();
  private Semaphore             cxlivres;
  private Semaphore             mutex        = new Semaphore(1, true);
  @Getter
  private int                   totalCaixas;
  @Getter
  private boolean               aberta;
  // cosmetic
  private String                nomes[]      = { "José", "Antônio", "Dorneles", "Atílio", "Fernando", "Bruce" };
  private String                sobrenomes[] = { "Silva", "Resende", "Parente", "Antunes", "Tobias", "Wayne" };
  private String                senhas[]     = { "A", "B", "C", "D", "1", "2", "3", "4", "5", "6", "7", "X", "K" };

  private int rnd(int max) {
    return (int) Math.floor(Math.random() * max);
  }

  public AgenciaBancaria(int numCaixas) {
    if (numCaixas <= 0)
      throw new RuntimeException("A agencia deve ter ao menos um caixa");
    log.info("Abrindo a agência");
    aberta = true;
    totalCaixas = numCaixas;
    int l1 = nomes.length;
    int l2 = sobrenomes.length;
    cxlivres = new Semaphore(numCaixas, true);
    while (numCaixas-- > 0) {
      String nome = nomes[rnd(l1)] + " " + sobrenomes[rnd(l2)];
      ProcessoCaixa cx = new ProcessoCaixa(nome, this);
      log.info("Novo caixa: " + cx);
      caixas.add(cx);
      cx.start();
    }
  }

  public ProcessoCliente recebeCliente(long i, long tempoAtendimento) {
    int l = senhas.length;
    String senha = senhas[rnd(l)] + senhas[rnd(l)] + senhas[rnd(l)] + senhas[rnd(l)];
    ProcessoCliente novoCliente = new ProcessoCliente(senha, i, tempoAtendimento, NaFila);
    log.info("Novo cliente: " + novoCliente);
    clientes.add(novoCliente);
    return novoCliente;
  }

  @SneakyThrows
  public void atendeProximoCliente() {
    mutex.acquire();
    if (clientes.size() == 0) {
      // log.info("Sem clientes no momento.");
      mutex.release();
      return;
    }
    cxlivres.acquire();
    ProcessoCaixa pc = caixas.remove(0);
    ProcessoCliente cli = clientes.remove(0);
    pc.setClienteAtual(cli);
    cli.setCaixaAtendimento(pc);
    mutex.release();
  }

  void finalizouAtendimento(ProcessoCaixa caixa) {
    caixas.add(caixa);
    cxlivres.release();
  }

  @SneakyThrows
  public void fechaAgencia() {
    aberta = false;
    String s = String.format("Expulsando da agência os %s clientes restantes", clientes.size());
    log.info(s);
    clientes.removeAll(clientes);
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
