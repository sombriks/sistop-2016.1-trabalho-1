package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.Button;

public class Inicio implements GameState {

  private Button btIniciar;

  public Inicio(ProcessingGUI context) {
    btIniciar = new Button("Iniciar", 320, 240,160,32){
      @Override
      public void onClick() {
       context.state("getcaixas"); 
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
