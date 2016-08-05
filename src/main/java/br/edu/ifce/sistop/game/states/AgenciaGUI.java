package br.edu.ifce.sistop.game.states;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifce.sistop.AgenciaBancaria;
import br.edu.ifce.sistop.ProcessoCaixa;
import br.edu.ifce.sistop.ProcessoCliente;
import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.PButton;
import br.edu.ifce.sistop.game.widgets.PCaixa;
import br.edu.ifce.sistop.game.widgets.PCliente;
import br.edu.ifce.sistop.game.widgets.PSprite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaGUI implements GameState {

  private AgenciaBancaria agencia;
  private PButton         btAddCliente;
  private PButton         btFecharAgencia;
  private long            numClientes = 1;
  private List<PSprite>   clientes    = new ArrayList<>();
  private List<PSprite>   caixas      = new ArrayList<>();
  private int             animSpeed   = 300;
  private int             nextTick    = 0;

  public AgenciaGUI(ProcessingGUI context, int numCaixas) {
    agencia = new AgenciaBancaria(numCaixas);
    for (ProcessoCaixa pc : agencia.getCaixas()) {
      String res = "open_chars" + rnd(11) + ".png";
      int px = (caixas.size() * 50) + 50;
      PCaixa p = inventaCaixa(context, res, px, 100);
      p.setCaixa(pc);
      caixas.add(p);

    }

    btAddCliente = new PButton("Adicionar Cliente", 100, 440, 160, 32) {
      @Override
      public void onClick() {
        log.info("Adicionar cliente randômico");
        ProcessoCliente cli = agencia.recebeCliente(numClientes++, (long) (5000 + Math.random() * 15));
        String res = "open_chars" + rnd(11) + ".png";
        int px = (clientes.size() * 50) + 50;
        PCliente p = inventaCliente(context, res, px, 400);
        p.setCliente(cli);
        clientes.add(p);
      }
    };
    btFecharAgencia = new PButton("Fechar Agência", 280, 440, 160, 32) {
      @Override
      public void onClick() {
        agencia.fechaAgencia();
        context.setCurrentState(new Inicio(context));
      }
    };
  }

  private PCliente inventaCliente(ProcessingGUI context, String res, int px, int py) {
    PCliente p = new PCliente(context, res, px, py);
    p.addFrame("down0", 0, 103, 32, 48);
    p.addFrame("down1", 36, 103, 32, 48);
    p.addFrame("down2", 73, 103, 32, 48);
    p.addAnimation("walkdown", "down1", "down0", "down1", "down2");
    return p;
  }

  private PCaixa inventaCaixa(ProcessingGUI context, String res, int px, int py) {
    PCaixa p = new PCaixa(context, res, px, py);
    p.addFrame("down0", 0, 103, 32, 48);
    p.addFrame("down1", 36, 103, 32, 48);
    p.addFrame("down2", 73, 103, 32, 48);
    p.addAnimation("walkdown", "down1", "down0", "down1", "down2");
    return p;
  }

  @Override
  public void draw(ProcessingGUI context) {
    int tick = context.millis();
    if (nextTick == 0 || tick > nextTick) {
      nextTick = tick + animSpeed;
      new Thread("AtendimentoDispatcher") {
        public void run() {

          agencia.atendeProximoCliente();
        };
      }.start();
    }
    context.background(255, 255, 255);
    context.textSize(12);
    btAddCliente.draw(context);
    btFecharAgencia.draw(context);
    for (PSprite cx : caixas)
      cx.draw(context);
    for (PSprite cli : clientes)
      cli.draw(context);

  }

  @Override
  public void keyTyped(ProcessingGUI context) {
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    btAddCliente.mouseClicked(context);
    btFecharAgencia.mouseClicked(context);
  }

  private int rnd(int max) {
    return (int) Math.floor(Math.random() * max);
  }
}
