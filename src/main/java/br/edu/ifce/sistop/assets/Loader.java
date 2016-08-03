package br.edu.ifce.sistop.assets;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import lombok.Cleanup;
import lombok.SneakyThrows;
import processing.core.PImage;

public enum Loader {
  INSTANCE;
  @SneakyThrows
  public PImage assetImg(String name) {
    @Cleanup
    InputStream in = Loader.class.getResourceAsStream(name);
    BufferedImage bin = ImageIO.read(in);
    return new PImage(bin);
  }
}
