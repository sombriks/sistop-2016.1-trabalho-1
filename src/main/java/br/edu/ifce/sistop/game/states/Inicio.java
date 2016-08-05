package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.PButton;

public class Inicio implements GameState {

  private PButton btIniciar;

  public Inicio(ProcessingGUI context) {
    btIniciar = new PButton("Iniciar", 320, 240, 160, 32) {
      @Override
      public void onClick() {
        context.setCurrentState(new GetCaixas(context));
      }
    };
  }

  @Override
  public void draw(ProcessingGUI context) {
    context.background(255, 255, 255);
    btIniciar.draw(context);
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    btIniciar.mouseClicked(context);
  }

  @Override
  public void keyTyped(ProcessingGUI context) {

  }

}
