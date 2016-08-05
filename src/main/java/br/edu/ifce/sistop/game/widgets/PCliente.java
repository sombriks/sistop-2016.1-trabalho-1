package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.ProcessoCliente;
import br.edu.ifce.sistop.game.ProcessingGUI;

public class PCliente extends PSprite {

  private ProcessoCliente cli;
  private PCaixa          atend;
  private int             nextTick;

  public PCliente(ProcessingGUI context, String resource, int x, int y) {
    super(context, resource, x, y);
  }

  @Override
  public void draw(ProcessingGUI context) {
    super.draw(context);
    int tick = context.millis();
    context.textSize(9);
    context.text("#" + cli.getId() + "\n" + "[" + cli.getSenha() + "]", x - 25, y - 65, 48, 48);
    context.text("[" + cli.getTempoAtendimento() / 1000 + " s]", x - 20, y + 5, 48, 48);
    context.text("[" + cli.getStatus() + "]", x - 20, y + 15, 64, 48);
    if (tick > nextTick) {
      if (atend != null) {
        if (atend.getX() > x)
          x += 10;
        if (atend.getX() < x)
          x -= 10;
        if (atend.getY() > y)
          y += 10;
        if (atend.getY() < y)
          y -= 10;
      }
      nextTick = tick + animSpeed / 10;
    }
  }

  public void setCliente(ProcessoCliente cli) {
    this.cli = cli;
    cli.setSprite(this);
  }

  public void walkTo(PCaixa sprite) {
    atend = sprite;
  }

}
