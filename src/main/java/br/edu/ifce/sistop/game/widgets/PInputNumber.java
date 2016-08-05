package br.edu.ifce.sistop.game.widgets;

import br.edu.ifce.sistop.game.ProcessingGUI;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import processing.core.PApplet;
import processing.core.PConstants;

@RequiredArgsConstructor
public abstract class PInputNumber implements TypeableWidget {

  @NonNull
  private Integer        x;

  @NonNull
  private Integer        y;

  @NonNull
  private Integer        maxLength;

  private @Getter String currentText = "";

  @Override
  public void draw(ProcessingGUI context) {
    context.textAlign(PConstants.CENTER, PConstants.CENTER);
    String compl = "";
    if (PApplet.second() % 2 == 0 && currentText.length() == 0)
      compl = "_";
    context.text(currentText + compl, x, y);
  }

  @Override
  public void keyTyped(ProcessingGUI context) {
    int curlen = currentText.length();
    if (context.key == PConstants.BACKSPACE && curlen > 0) {
      currentText = currentText.substring(0, curlen - 1);
      return;
    }
    if (curlen >= maxLength)
      return;
    if (Character.isDigit(context.key))
      currentText += context.key;
    onTyped();
  }
  
  public int getNumber(){
    if(currentText.matches("\\d+"))
      return new Integer(currentText);
    return 0;
  }

  public abstract void onTyped();
}
