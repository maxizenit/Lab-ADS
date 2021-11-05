package ru.maxizenit.lab5.model.shape;

import lombok.Getter;
import lombok.Setter;
import ru.maxizenit.lab5.dict.LineOrientation;
import ru.maxizenit.lab5.model.field.Field;

@Getter
@Setter
public class PlainCross extends Cross implements Rotatable {

  protected int width;
  protected int height;

  public PlainCross(Field field, Point startPoint, int width, int height) {
    super(field, startPoint);

    firstLine =
        new Line(
            field,
            new Point(startPoint.getX(), startPoint.getY() + height / 2),
            width,
            LineOrientation.HORIZONTAL);
    secondLine =
        new Line(
            field,
            new Point(startPoint.getX() + width / 2, startPoint.getY()),
            height,
            LineOrientation.VERTICAL);
  }

  @Override
  public void rotate() {
    int temp = width;
    width = height;
    height = temp;

    firstLine.setLength(width);
    secondLine.setLength(height);

    firstLine.setStartPoint(new Point(startPoint.getX(), startPoint.getY() + height / 2));
    secondLine.setStartPoint(new Point(startPoint.getX() + width / 2, startPoint.getY()));
  }
}
