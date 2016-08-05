package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.ProcessoCaixa;
import br.edu.ifce.sistop.game.ProcessingGUI;

public class PCaixa extends PSprite {

  private ProcessoCaixa cx;

  public PCaixa(ProcessingGUI context, String resource, int x, int y) {
    super(context, resource, x, y);
  }

  @Override
  public void draw(ProcessingGUI context) {
    super.draw(context);
    
  }

  public void setCaixa(ProcessoCaixa cx) {
    this.cx = cx;
  }

}
