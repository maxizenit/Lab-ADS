package ru.maxizenit.lab6.model.shape;

import ru.maxizenit.lab6.dict.LineOrientation;
import ru.maxizenit.lab6.model.field.Field;

public class Rectangle extends Tetragon implements Rotatable {

  public Rectangle(Field field, Point startPoint, int width, int height) {
    super(field, startPoint, width, height);

    topLine = new Line(field, startPoint, width, LineOrientation.HORIZONTAL);
    bottomLine =
        new Line(
            field,
            new Point(startPoint.getX(), startPoint.getY() + height - 1),
            width,
            LineOrientation.HORIZONTAL);

    leftLine = new Line(field, startPoint, height, LineOrientation.VERTICAL);
    rightLine =
        new Line(
            field,
            new Point(startPoint.getX() + width - 1, startPoint.getY()),
            height,
            LineOrientation.VERTICAL);
  }

  @Override
  public void rotate() {
    int newWidth = height;
    int newHeight = width;

    setWidth(newWidth);
    setHeight(newHeight);
  }

  @Override
  public String toString() {
    return String.format(
        "%d) Прямоугольник (x: %d, y: %d, ширина: %d, высота: %d)",
        id, startPoint.getX(), startPoint.getY(), width, height);
  }
}
