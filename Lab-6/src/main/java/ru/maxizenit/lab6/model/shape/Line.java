package ru.maxizenit.lab6.model.shape;

import lombok.Getter;
import lombok.Setter;
import ru.maxizenit.lab6.dict.LineOrientation;
import ru.maxizenit.lab6.model.field.Field;

@Getter
@Setter
public class Line extends Shape implements Rotatable {

  protected int length;
  protected LineOrientation lineOrientation;

  public Line(Field field, Point startPoint, int length, LineOrientation lineOrientation) {
    super(field, startPoint);
    this.length = length;
    this.lineOrientation = lineOrientation;
  }

  @Override
  public void draw() {
    if (lineOrientation.equals(LineOrientation.HORIZONTAL)
        && startPoint.getY() >= 0
        && startPoint.getY() < field.getHeight()) {
      StringBuilder sb = new StringBuilder(field.getMatrix()[startPoint.getY()]);

      for (int i = startPoint.getX(); i < startPoint.getX() + length; ++i) {
        if (i < 0 || i >= field.getWidth()) {
          continue;
        }

        sb.setCharAt(i, field.getDrawSymbol());
      }

      field.getMatrix()[startPoint.getY()] = sb.toString();
    } else if (lineOrientation.equals(LineOrientation.VERTICAL)
        && startPoint.getX() >= 0
        && startPoint.getX() < field.getWidth()) {
      for (int i = startPoint.getY(); i < startPoint.getY() + length; ++i) {
        if (i < 0 || i >= field.getHeight()) {
          continue;
        }

        StringBuilder sb = new StringBuilder(field.getMatrix()[i]);

        sb.setCharAt(startPoint.getX(), field.getDrawSymbol());
        field.getMatrix()[i] = sb.toString();
      }
    } else if (lineOrientation.equals(LineOrientation.UPDIAGONAL)) {
      for (int i = startPoint.getY(); i < startPoint.getY() + length; ++i) {
        if (i < 0 || i >= field.getHeight()) {
          continue;
        }

        int currentX = startPoint.getX() + length - 1 - (i - startPoint.getY());

        if (currentX < 0 || currentX >= field.getWidth()) {
          continue;
        }

        StringBuilder sb = new StringBuilder(field.getMatrix()[i]);

        sb.setCharAt(currentX, field.getDrawSymbol());
        field.getMatrix()[i] = sb.toString();
      }
    } else {
      for (int i = startPoint.getY(); i < startPoint.getY() + length; ++i) {
        if (i < 0 || i >= field.getHeight()) {
          continue;
        }

        int currentX = startPoint.getX() + length - 1 - (i - startPoint.getY());

        if (currentX < 0 || currentX >= field.getWidth()) {
          continue;
        }

        StringBuilder sb = new StringBuilder(field.getMatrix()[i]);

        sb.setCharAt(currentX, field.getDrawSymbol());
        field.getMatrix()[i] = sb.toString();
      }
    }
  }

  @Override
  public void rotate() {
    lineOrientation = LineOrientation.negative(lineOrientation);
  }

  @Override
  public String toString() {
    return String.format(
        "%d) Линия (x: %d, y: %d, длина: %d, ориентация: %s)",
        id, startPoint.getX(), startPoint.getY(), length, lineOrientation);
  }
}
