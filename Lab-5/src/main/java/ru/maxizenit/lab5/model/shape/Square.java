package ru.maxizenit.lab5.model.shape;

import ru.maxizenit.lab5.dict.LineOrientation;
import ru.maxizenit.lab5.model.field.Field;

public class Square extends Tetragon {

  public Square(Field field, Point startPoint, int side) {
    super(field, startPoint, side, side);

    topLine = new Line(field, startPoint, side, LineOrientation.HORIZONTAL);
    bottomLine =
        new Line(
            field,
            new Point(startPoint.getX(), startPoint.getY() + side - 1),
            side,
            LineOrientation.HORIZONTAL);

    leftLine = new Line(field, startPoint, side, LineOrientation.VERTICAL);
    rightLine =
        new Line(
            field,
            new Point(startPoint.getX() + side - 1, startPoint.getY()),
            side,
            LineOrientation.VERTICAL);
  }

  @Override
  public void setWidth(int width) {
    super.setWidth(width);
    super.setHeight(width);
  }

  @Override
  public void setHeight(int height) {
    setWidth(height);
  }

  @Override
  public String toString() {
    return String.format(
        "%d) Квадрат (x: %d, y: %d, длина стороны: %d)",
        id, startPoint.getX(), startPoint.getY(), width);
  }
}
