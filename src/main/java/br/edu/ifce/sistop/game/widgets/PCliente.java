package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.ProcessoCliente;
import br.edu.ifce.sistop.game.ProcessingGUI;

public class PCliente extends PSprite {

  public PCliente(ProcessingGUI context, String resource, int x, int y) {
    super(context, resource, x, y);
  }

  private ProcessoCliente cli;
  
  @Override
  public void draw(ProcessingGUI context) {
    super.draw(context);
  }

  public void setCliente(ProcessoCliente cli) {
    this.cli = cli;
  }

}
