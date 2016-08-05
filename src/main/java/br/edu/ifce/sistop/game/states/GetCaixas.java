package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.InputNumber;
import processing.core.PConstants;

public class GetCaixas implements GameState {

  private InputNumber iNumber;

  public GetCaixas(ProcessingGUI context) {
    iNumber = new InputNumber(320, 220, 3) {
      @Override
      public void onTyped() {
        int numCaixas = iNumber.getNumber();
        System.out.println("Caixas iniciais: " + numCaixas);
        context.setCurrentState(new AgenciaGUI(context, numCaixas));
      }
    };
  }

  @Override
  public void draw(ProcessingGUI context) {
    context.textAlign(PConstants.CENTER, PConstants.CENTER);
    context.background(255, 255, 255);
    context.textSize(24);
    context.text("Digite a quantidade de caixas do banco (1 - 9)", 320, 160);
    iNumber.draw(context);
  }

  @Override
  public void mouseClicked(ProcessingGUI context) {

  }

  @Override
  public void keyTyped(ProcessingGUI context) {
    iNumber.keyTyped(context);
  }
}
