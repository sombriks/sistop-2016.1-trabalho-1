package br.edu.ifce.sistop.gui;

public class InitialState implements GameState {

  private GameButton btIniciar;

  private boolean    reseted;

  @Override
  public void reset(ProcessingGUI context) {
    btIniciar = new GameButton("Iniciar", 320, 240,160,32);
    reseted = true;
  }

  @Override
  public void draw(ProcessingGUI context) {
    if (!reseted)
      reset(context);
    btIniciar.draw(context);
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    btIniciar.mouseClicked(context);
  }

}
