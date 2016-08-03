package br.edu.ifce.sistop.gui;

public interface GameState {
  
  void reset(ProcessingGUI context);
    
  void draw(ProcessingGUI context);

  void mouseClicked(ProcessingGUI context);
  
}
