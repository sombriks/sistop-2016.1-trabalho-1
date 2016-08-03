package br.edu.ifce.sistop.gui;

import br.edu.ifce.sistop.assets.Loader;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import processing.core.PConstants;
import processing.core.PImage;

@Data
@RequiredArgsConstructor
public class GameButton {

  @NonNull
  private String  label;
  @NonNull
  private Integer x;
  @NonNull
  private Integer y;
  @NonNull
  private Integer w;
  @NonNull
  private Integer h;

  private PImage  fundo = Loader.INSTANCE.assetImg("fundo_botao.png");

  public void draw(ProcessingGUI context) {
    context.imageMode(PConstants.CENTER);
    context.textAlign(PConstants.CENTER, PConstants.CENTER);
    context.image(fundo, x, y, w, h);
    context.fill(0, 0, 0);
    context.text(label, x, y);
  }

  public void mouseClicked(ProcessingGUI context) {
    int dx = context.mouseX - x;
    int dy = context.mouseY - y;
    if (Math.sqrt(Math.pow(dx, 2)) <= w / 2) {
      if(Math.sqrt(Math.pow(dy, 2)) <= h /2 ){
        System.out.println("Clicked!");
      }
    }
  }

}
