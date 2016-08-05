package br.edu.ifce.sistop.game.widgets;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifce.sistop.assets.Loader;
import br.edu.ifce.sistop.game.ProcessingGUI;
import processing.core.PImage;

public class PSprite implements Widget {

  // private ProcessingGUI context;
  protected int                 x;
  protected int                 y;
  private PImage                baseImg;
  private Map<String, PImage>   frames     = new HashMap<>();
  private PImage                currentFrame;
  private Map<String, String[]> animations = new HashMap<>();
  private String                currentAnimation;
  private int                   frameKey;
  private int                   animSpeed  = 300;
  private int                   nextTick;

  public PSprite(ProcessingGUI context, String resource, int x, int y) {
    baseImg = Loader.INSTANCE.assetImg(resource);
    this.x = x;
    this.y = y;
  }

  public PSprite addFrame(String framename, int x, int y, int w, int h) {
    currentFrame = baseImg.get(x, y, w, h);
    frames.put(framename, currentFrame);
    return this;
  }

  @Override
  public void draw(ProcessingGUI context) {
    int tick = context.millis();
    if (nextTick == 0)
      nextTick = tick + animSpeed;
    if (currentAnimation != null) {
      String[] framenames = animations.get(currentAnimation);
      if (framenames.length <= frameKey)
        frameKey = 0;
      if (framenames != null && framenames.length > frameKey) {
        currentFrame = frames.get(framenames[frameKey]);
        if (tick > nextTick) {
          frameKey++;
          nextTick = tick + animSpeed;
        }
      }
    }
    if (currentFrame != null)
      context.image(currentFrame, x, y);
  }

  public void addAnimation(String name, String... seqframes) {
    currentAnimation = name;
    animations.put(name, seqframes);
  }

}
