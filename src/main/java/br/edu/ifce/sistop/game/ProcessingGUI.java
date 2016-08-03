package br.edu.ifce.sistop.game;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifce.sistop.game.states.GameState;
import br.edu.ifce.sistop.game.states.GetCaixas;
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

  private GameState              current, prev;
  private Map<String, GameState> states = new HashMap<>();

  @Override
  public void settings() {
    size(640, 480);
  }

  public void setup() {
    frameRate(60);
    states.put("inicio", new Inicio(this));
    states.put("getcaixas", new GetCaixas(this));
    current = states.get("inicio");
    prev = current;
  }

  public void draw() {
    if (prev != current) {
      clear();
      prev = current;
    }
    current.draw(this);
  }

  @Override
  public void mouseClicked() {
    current.mouseClicked(this);
  }
  
  @Override
  public void keyTyped(KeyEvent event) {
    current.keyTyped(this);
  }

  public void state(String nextState) {
    GameState state = states.get(nextState);
    if (state == null)
      throw new RuntimeException("o estado " + nextState + " não estixte");
    current = state;
  }
}
