package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.AgenciaBancaria;
import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.PButton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaGUI implements GameState {

  private AgenciaBancaria agencia;
  private PButton         btAddCliente;
  private PButton         btFecharAgencia;
  private long            numClientes = 1;

  public AgenciaGUI(ProcessingGUI context, int numCaixas) {
    agencia = new AgenciaBancaria(numCaixas);
    btAddCliente = new PButton("Adicionar Cliente", 100, 440, 160, 32) {
      @Override
      public void onClick() {
        log.info("Adicionar cliente randômico");
        agencia.recebeCliente(numClientes++, (long) (5000 + Math.random() * 15));
      }
    };
    btFecharAgencia = new PButton("Fechar Agência",280,440,160,32){
      @Override
      public void onClick() {
        agencia.fechaAgencia();
        context.setCurrentState(new Inicio(context));
      }
    };
  }

  @Override
  public void draw(ProcessingGUI context) {
    context.background(255, 255, 255);
    context.textSize(12);
    btAddCliente.draw(context);
    btFecharAgencia.draw(context);
  }

  @Override
  public void keyTyped(ProcessingGUI context) {
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    btAddCliente.mouseClicked(context);
    btFecharAgencia.mouseClicked(context);
  }

}
