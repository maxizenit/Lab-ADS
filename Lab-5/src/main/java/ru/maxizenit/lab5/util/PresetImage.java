package ru.maxizenit.lab5.util;

import ru.maxizenit.lab5.dict.LineOrientation;
import ru.maxizenit.lab5.dict.SemicircleOrientation;
import ru.maxizenit.lab5.model.field.Field;
import ru.maxizenit.lab5.model.shape.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PresetImage {

  private static final Field field;

  static {
    field = new Field(34, 30, '*', '.');
    List<Shape> shapes = new ArrayList<>();

    shapes.add(new SemicircleWithPlainCross(field, new Point(13, 5), 4, SemicircleOrientation.UP));
    shapes.add(new Rectangle(field, new Point(13, 9), 9, 3));
    shapes.add(new Rectangle(field, new Point(10, 11), 15, 10));
    shapes.add(new Line(field, new Point(8, 11), 19, LineOrientation.HORIZONTAL));
    shapes.add(new Line(field, new Point(13, 14), 2, LineOrientation.HORIZONTAL));
    shapes.add(new Line(field, new Point(20, 14), 2, LineOrientation.HORIZONTAL));
    shapes.add(new Line(field, new Point(17, 16), 1, LineOrientation.HORIZONTAL));
    shapes.add(new Line(field, new Point(15, 18), 5, LineOrientation.HORIZONTAL));
    shapes.add(new Semicircle(field, new Point(8, 12), 2, SemicircleOrientation.LEFT));
    shapes.add(new Semicircle(field, new Point(24, 12), 2, SemicircleOrientation.RIGHT));
    shapes.add(new Parallelogram(field, new Point(7, 8), 2, 3));
    shapes.add(new Parallelogram(field, new Point(24, 8), 2, 3));
    ((Reflectable) shapes.get(shapes.size() - 1)).reflect();
    shapes.add(new Parallelogram(field, new Point(5, 21), 3, 3));
    ((Reflectable) shapes.get(shapes.size() - 1)).reflect();
    shapes.add(new Parallelogram(field, new Point(25, 21), 3, 3));

    shapes.forEach(Shape::draw);
  }

  public static void show(PrintStream printStream) {
    field.show(printStream);
  }
}
