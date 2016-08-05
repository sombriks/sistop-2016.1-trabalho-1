package br.edu.ifce.sistop.game.states;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifce.sistop.AgenciaBancaria;
import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.PButton;
import br.edu.ifce.sistop.game.widgets.PSprite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaGUI implements GameState {

  private AgenciaBancaria agencia;
  private PButton         btAddCliente;
  private PButton         btFecharAgencia;
  private long            numClientes = 1;
  private String          resources[] = { "Dotty", "Frichim", "Jorge", "Oswaldo", "Roberto" };
  private List<PSprite>   clientes    = new ArrayList<>();
  private List<PSprite>   caixas      = new ArrayList<>();
  private int             animSpeed   = 300;
  private int             nextTick    = 0;

  public AgenciaGUI(ProcessingGUI context, int numCaixas) {
    agencia = new AgenciaBancaria(numCaixas);
    for(ProcessoCaixa pc : agencia.getCaixas())
    while (numCaixas-- > 0) {
      int len = resources.length;
      String res = resources[rnd(len)] + ".png";
      int px = (caixas.size() * 50) + 50;
      caixas.add(inventaPessoa(context, res, px, 100));
    }

    btAddCliente = new PButton("Adicionar Cliente", 100, 440, 160, 32) {
      @Override
      public void onClick() {
        log.info("Adicionar cliente randômico");
        agencia.recebeCliente(numClientes++, (long) (5000 + Math.random() * 15));
        int len = resources.length;
        String res = resources[rnd(len)] + ".png";
        int px = (clientes.size() * 50) + 50;
        clientes.add(inventaPessoa(context, res, px, 400));
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

  private PSprite inventaPessoa(ProcessingGUI context, String res, int px, int py) {
    PSprite p = new PSprite(context, res, px, py);
    p.addFrame("down0", 0, 103, 32, 48);
    p.addFrame("down1", 36, 103, 32, 48);
    p.addFrame("down2", 73, 103, 32, 48);
    p.addAnimation("walkdown", "down1", "down0", "down1", "down2");
    return p;
  }

  @Override
  public void draw(ProcessingGUI context) {
    int tick = context.millis();
    if (nextTick == 0 || tick > nextTick){
      nextTick = tick + animSpeed;
      agencia.atendeProximoCliente();
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
