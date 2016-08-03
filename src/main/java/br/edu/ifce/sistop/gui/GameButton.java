package br.edu.ifce.sistop.gui;

import br.edu.ifce.sistop.assets.Loader;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import processing.core.PImage;

@Data
@RequiredArgsConstructor
public class GameButton {
  
  @NonNull
  private String label;
  @NonNull
  private Integer x;
  @NonNull 
  private Integer y;
  
  private PImage fundo = Loader.INSTANCE.assetImg("fundo_botao.png");
    
  public void draw(ProcessingGUI context){
    context.image(fundo, x, y);
    context.fill(0, 0, 0);
    context.text(label, x, y);      
  }

  public void mouseClicked(ProcessingGUI context) {
    System.out.println(this);
    System.out.println(context);
  }
  
}
