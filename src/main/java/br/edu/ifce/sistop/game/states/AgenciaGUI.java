package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.AgenciaBancaria;
import br.edu.ifce.sistop.game.ProcessingGUI;

public class AgenciaGUI implements GameState {

  private AgenciaBancaria agencia;

  public AgenciaGUI(ProcessingGUI context, int numCaixas) {
    agencia = new AgenciaBancaria(numCaixas);
    agencia.setAberta(true);
    new Thread() {
      public void run() {
        while (agencia.isAberta())
          agencia.atendeProximoCliente();
      };
    }.start();
  }

  @Override
  public void keyTyped(ProcessingGUI context) {

  }

  @Override
  public void draw(ProcessingGUI context) {

  }

  @Override
  public void mouseClicked(ProcessingGUI context) {

  }

}
