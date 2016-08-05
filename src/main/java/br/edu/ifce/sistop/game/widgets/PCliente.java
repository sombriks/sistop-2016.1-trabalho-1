package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.ProcessoCliente;
import br.edu.ifce.sistop.game.ProcessingGUI;
import br.edu.ifce.sistop.game.states.AgenciaGUI;

public class PCliente extends PSprite {

  private ProcessoCliente cli;
  private boolean         done;
  private PCaixa          atend;
  private int             nextTick;
  private AgenciaGUI      agenciaGUI;

  public PCliente(AgenciaGUI agenciaGUI, String resource, int x, int y) {
    super(resource, x, y);
    this.agenciaGUI = agenciaGUI;
  }

  @Override
  public void draw(ProcessingGUI context) {
    super.draw(context);
    int tick = context.millis();
    context.textSize(9);
    context.text("#" + cli.getId() + "\n" + "[" + cli.getSenha() + "]", x - 25, y - 65, 48, 48);
    context.text("[" + cli.getTempoAtendimento()/1000l + " s]", x - 20, y + 5, 48, 48);
    context.text("[" + cli.getStatus() + "]", x - 20, y + 15, 64, 48);
    if (tick > nextTick) {
      if (atend != null) {
        if (atend.getX() > x)
          x += 10;
        if (atend.getX() < x)
          x -= 10;
        if (atend.getY() + 96 > y)
          y += 10;
        if (atend.getY() < y)
          y -= 10;
      }
      if (done) {
        y += 10;
        if (y > context.height + 64) {
          // offScreen
          agenciaGUI.remove(this);
        }
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

  public void goAway() {
    atend = null;
    done = true;
  }

}
