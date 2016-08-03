package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.game.ProcessingGUI;

public class GetCaixas implements GameState {

  public GetCaixas(ProcessingGUI processingGUI) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void draw(ProcessingGUI context) {
    context.background(255, 255, 255);
    context.textSize(24);
    context.text("Digite a quantidade de caixas do banco", 320, 200);
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {
    
  }

  @Override
  public void keyTyped(ProcessingGUI context) {
    
  }
}
