package br.edu.ifce.sistop;

import java.sql.Timestamp;

import br.edu.ifce.sistop.game.widgets.PCliente;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@RequiredArgsConstructor
@ToString(exclude = { "sprite", "caixaAtendimento" })
public class ProcessoCliente {

  @NonNull
  private String        senha;
  @NonNull
  private Long          id;
  @NonNull
  private Long          tempoAtendimento;
  @NonNull
  private StatusCliente status;
  //
  private ProcessoCaixa caixaAtendimento;
  // i am very sorry
  private PCliente      sprite;

  /**
   * cliente pagando as contas
   * 
   * @param t1
   *          tempo em que ele começou a pagar as contas
   * @return tempo em que ele terminou de pagar as contas
   */
  long pagaContas(long t1, ProcessoCaixa caixaAtendimento) {
    this.caixaAtendimento = caixaAtendimento;
    // really hope you forgive-me;
    if (this.sprite != null)
      this.sprite.walkTo(this.caixaAtendimento.getSprite());

    status = StatusCliente.EmAtendimento;
    boolean sorteandoboleto = true;
    long t2 = tempoAtendimento + t1;
    long t3 = 0;
    String s = "O cliente (%s) de senha %s entrou em atendimento em %s com o caixa %s";
    s = String.format(s, id, senha, new Timestamp(t1), caixaAtendimento);
    log.info(s);
    while (sorteandoboleto) {
      // pagando boleto, falando mal do governo, etc...
      t3 = System.currentTimeMillis();
      if (t2 <= t3)
        sorteandoboleto = false;
      tempoAtendimento--;
    }
    tempoAtendimento = 0l;
    status = StatusCliente.Atendido;
    s = "O cliente %s de senha %s finalizou atendimento em %s";
    s = String.format(s, id, senha, new Timestamp(t3));
    log.info(s);
    return t3;
  }
}
