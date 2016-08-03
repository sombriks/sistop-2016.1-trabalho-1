package br.edu.ifce.sistop.gui;

import processing.core.PConstants;

public class InitialState implements GameState {

  private GameButton btIniciar;

  @Override
  public void reset(ProcessingGUI context) {
    context.imageMode(PConstants.CENTER);
    context.textAlign(PConstants.CENTER, PConstants.CENTER);
    btIniciar = new GameButton("Iniciar", 320, 240);
  }

  @Override
  public void draw(ProcessingGUI context) {
    btIniciar.draw(context);
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    btIniciar.mouseClicked(context);
  }

}
