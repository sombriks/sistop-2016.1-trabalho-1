package br.edu.ifce.sistop.game;

import br.edu.ifce.sistop.game.states.GameState;
import br.edu.ifce.sistop.game.states.Inicio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * Visual Awesomenes
 * 
 * @author sombriks
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessingGUI extends PApplet {

  private GameState currentState, prevState;

  @Override
  public void settings() {
    size(640, 480);
  }

  public void setup() {
    frameRate(60);
    currentState = new Inicio(this);
    prevState = currentState;
  }

  public void draw() {
    if (prevState != currentState) {
      clear();
      prevState = currentState;
    }
    currentState.draw(this);
  }

  @Override
  public void mouseClicked() {
    currentState.mouseClicked(this);
  }

  @Override
  public void keyTyped(KeyEvent event) {
    currentState.keyTyped(this);
  }
}
