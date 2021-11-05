package ru.maxizenit.lab6.model.shape;

import lombok.Getter;
import ru.maxizenit.lab6.model.field.Field;

public abstract class Tetragon extends Shape {

  @Getter protected int width;

  @Getter protected int height;

  protected Line topLine;
  protected Line bottomLine;
  protected Line leftLine;
  protected Line rightLine;

  public Tetragon(Field field, Point startPoint, int width, int height) {
    super(field, startPoint);
    this.width = width;
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;

    topLine.length = width;
    bottomLine.length = width;

    rightLine.setStartPoint(
        new Point(leftLine.getStartPoint().getX() + width - 1, rightLine.getStartPoint().getY()));
  }

  public void setHeight(int height) {
    this.height = height;

    leftLine.setLength(height);
    rightLine.setLength(height);

    bottomLine.setStartPoint(
        new Point(bottomLine.getStartPoint().getX(), topLine.getStartPoint().getY() + height - 1));
  }

  @Override
  public void draw() {
    topLine.draw();
    bottomLine.draw();
    leftLine.draw();
    rightLine.draw();
  }
}
