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
    context.textSize(9);
    context.text(cx.getNome(), x - 25, y - 65, 48, 48);
    context.text("[" + cx.getAtendidos() + "]", x - 25, y + 5, 48, 48);

  }

  public void setCaixa(ProcessoCaixa cx) {
    this.cx = cx;
    cx.setSprite(this);
  }

}
