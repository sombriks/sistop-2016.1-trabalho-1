package br.edu.ifce.sistop.gui;

import lombok.Data;
import lombok.EqualsAndHashCode;
import processing.core.PApplet;

/**
 * Visual Awesomenes
 * 
 * @author sombriks
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessingGUI extends PApplet {

  private GameState    current;
  private InitialState initial;

  @Override
  public void settings() {
    size(640, 480);
  }

  public void setup() {
    frameRate(60);
    initial = new InitialState();
    initial.reset(this);
    current = initial;
  }

  public void draw() {
    current.draw(this);
  }

  @Override
  public void mouseClicked() {
    current.mouseClicked(this);
  }
}
