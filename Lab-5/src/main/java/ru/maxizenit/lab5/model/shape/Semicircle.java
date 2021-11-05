package ru.maxizenit.lab5.model.shape;

import lombok.Getter;
import ru.maxizenit.lab5.dict.LineOrientation;
import ru.maxizenit.lab5.dict.SemicircleOrientation;
import ru.maxizenit.lab5.model.field.Field;

import static ru.maxizenit.lab5.dict.SemicircleOrientation.*;

public class Semicircle extends Circle implements Rotatable, Reflectable {

  @Getter protected SemicircleOrientation semicircleOrientation;
  protected Line line;
  protected int maxX;
  protected int maxY;

  public Semicircle(
      Field field, Point startPoint, int radius, SemicircleOrientation semicircleOrientation) {
    super(field, startPoint, radius);
    setSemicircleOrientation(semicircleOrientation);
  }

  @Override
  public void setRadius(int radius) {
    super.setRadius(radius);
    line.setLength(radius * 2);
  }

  public void setSemicircleOrientation(SemicircleOrientation semicircleOrientation) {
    this.semicircleOrientation = semicircleOrientation;

    LineOrientation lineOrientation;
    Point linePoint;

    if (semicircleOrientation.equals(UP)
        || semicircleOrientation.equals(DOWN)) {
      lineOrientation = LineOrientation.HORIZONTAL;
      maxX = startPoint.getX() + radius * 2;
      maxY = startPoint.getY() + radius;

      if (semicircleOrientation.equals(UP)) {
        linePoint = new Point(startPoint.getX(), startPoint.getY() + radius);

      } else {
        linePoint = startPoint;
        center = new Point(startPoint.getX() + radius, startPoint.getY());

      }
    } else {
      lineOrientation = LineOrientation.VERTICAL;
      maxX = startPoint.getX() + radius;
      maxY = startPoint.getY() + radius * 2;

      if (semicircleOrientation.equals(SemicircleOrientation.LEFT)) {
        linePoint = new Point(startPoint.getX() + radius, startPoint.getY());

      } else {
        linePoint = startPoint;
        center = new Point(startPoint.getX(), startPoint.getY() + radius);
      }
    }

    line = new Line(field, linePoint, radius * 2, lineOrientation);
  }

  @Override
  public void draw() {
    line.draw();

    for (int i = 1; i < 360; ++i) {
      double angle = (double) i / 180 * Math.PI;
      int x = (int) Math.round(center.getX() + radius * Math.cos(angle));
      int y = (int) Math.round(center.getY() + radius * Math.sin(angle));

      if (x < startPoint.getX() || y < startPoint.getY()) {
        continue;
      }

      if (x > maxX || y > maxY) {
        continue;
      }

      StringBuilder sb = new StringBuilder(field.getMatrix()[y]);
      sb.setCharAt(x, field.getDrawSymbol());
      field.getMatrix()[y] = sb.toString();
    }
  }

  @Override
  public void reflect() {
    setSemicircleOrientation(SemicircleOrientation.negative(semicircleOrientation));
  }

  @Override
  public void rotate() {
    setSemicircleOrientation(switch (semicircleOrientation) {
      case UP -> RIGHT;
      case DOWN -> LEFT;
      case LEFT -> UP;
      case RIGHT -> DOWN;
    });
  }
}
