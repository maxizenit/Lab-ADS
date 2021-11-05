package ru.maxizenit.lab5.util;

import ru.maxizenit.lab5.dict.LineOrientation;
import ru.maxizenit.lab5.dict.SemicircleOrientation;
import ru.maxizenit.lab5.model.field.Field;
import ru.maxizenit.lab5.model.shape.*;

public class ShapeFactory {

  private final Field field;

  public ShapeFactory(Field field) {
    this.field = field;
  }

  public Line createLine(String[] args) throws Exception {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int length = Integer.parseInt(args[2]);
    LineOrientation lineOrientation = LineOrientation.parseLineOrientation(args[3]);

    return new Line(field, new Point(x, y), length, lineOrientation);
  }

  public Rectangle createRectangle(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int width = Integer.parseInt(args[2]);
    int height = Integer.parseInt(args[3]);

    return new Rectangle(field, new Point(x, y), width, height);
  }

  public Square createSquare(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int side = Integer.parseInt(args[2]);

    return new Square(field, new Point(x, y), side);
  }

  public Parallelogram createParallelogram(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int width = Integer.parseInt(args[2]);
    int height = Integer.parseInt(args[3]);

    return new Parallelogram(field, new Point(x, y), width, height);
  }

  public Circle createCircle(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int radius = Integer.parseInt(args[2]);

    return new Circle(field, new Point(x, y), radius);
  }

  public Semicircle createSemicircle(String[] args) throws Exception {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int radius = Integer.parseInt(args[2]);
    SemicircleOrientation orientation = SemicircleOrientation.parseSemicircleOrientation(args[3]);

    return new Semicircle(field, new Point(x, y), radius, orientation);
  }

  public SemicircleWithPlainCross createSemicircleWithPlainCross(String[] args) throws Exception {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int radius = Integer.parseInt(args[2]);
    SemicircleOrientation orientation = SemicircleOrientation.parseSemicircleOrientation(args[3]);

    return new SemicircleWithPlainCross(field, new Point(x, y), radius, orientation);
  }

  public PlainCross createPlainCross(String args[]) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
    int width = Integer.parseInt(args[2]);
    int height = Integer.parseInt(args[3]);

    return new PlainCross(field, new Point(x, y), width, height);
  }
}
