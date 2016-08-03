package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.game.ProcessingGUI;

public interface TypeableWidget extends Widget{

  void keyTyped(ProcessingGUI context);

}