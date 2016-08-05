package br.edu.ifce.sistop.game.states;

import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.widgets.PInputNumber;
import lombok.extern.slf4j.Slf4j;
import processing.core.PConstants;

@Slf4j
public class GetCaixas implements GameState {

  private PInputNumber iNumber;

  public GetCaixas(ProcessingGUI context) {
    iNumber = new PInputNumber(320, 220, 3) {
      @Override
      public void onTyped() {
        int numCaixas = iNumber.getNumber();
        log.info("Caixas iniciais: " + numCaixas);
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
