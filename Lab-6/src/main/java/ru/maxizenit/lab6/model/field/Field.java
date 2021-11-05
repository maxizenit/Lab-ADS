package ru.maxizenit.lab6.model.field;

import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.util.Arrays;

@Getter
public class Field {

  private int width;
  private int height;

  @Setter private char drawSymbol;
  @Setter private char emptySymbol;

  private final String[] matrix;

  public Field(int width, int height, char drawSymbol, char emptySymbol) {
    this.width = width;
    this.height = height;
    this.drawSymbol = drawSymbol;
    this.emptySymbol = emptySymbol;

    matrix = new String[height];
    clear();
  }

  public void setWidth(int width) {
    this.width = width;
    clear();
  }

  public void setHeight(int height) {
    this.height = height;
    clear();
  }

  public void clear() {
    for (int i = 0; i < height; ++i) {
      matrix[i] = Character.toString(emptySymbol).repeat(width);
    }
  }

  public void show(PrintStream printStream) {
    Arrays.stream(matrix).forEach(printStream::println);
  }
}
