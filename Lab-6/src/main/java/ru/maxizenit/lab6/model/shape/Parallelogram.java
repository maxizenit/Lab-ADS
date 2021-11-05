package ru.maxizenit.lab6.model.shape;

import ru.maxizenit.lab6.dict.LineOrientation;
import ru.maxizenit.lab6.model.field.Field;

public class Parallelogram extends Tetragon implements Rotatable, Reflectable {

  public Parallelogram(Field field, Point startPoint, int width, int height) {
    super(field, startPoint, width, height);

    topLine = new Line(field, startPoint, width, LineOrientation.HORIZONTAL);
    bottomLine =
        new Line(
            field,
            new Point(startPoint.getX() + height - 1, startPoint.getY() + height - 1),
            width,
            LineOrientation.HORIZONTAL);

    leftLine = new Line(field, startPoint, height, LineOrientation.DOWNDIAGONAL);
    rightLine =
        new Line(
            field,
            new Point(startPoint.getX() + width - 1, startPoint.getY()),
            height,
            LineOrientation.DOWNDIAGONAL);
  }

  @Override
  public void reflect() {
    if (leftLine.getLineOrientation().equals(LineOrientation.UPDIAGONAL)
        || leftLine.getLineOrientation().equals(LineOrientation.DOWNDIAGONAL)) {
      leftLine.rotate();
      rightLine.rotate();

      int newTopLineX = bottomLine.getStartPoint().getX();
      int newBottomLineX = topLine.getStartPoint().getX();

      topLine.setStartPoint(new Point(newTopLineX, topLine.getStartPoint().getY()));
      bottomLine.setStartPoint(new Point(newBottomLineX, bottomLine.getStartPoint().getY()));
    } else {
      topLine.rotate();
      bottomLine.rotate();

      int newLeftLineY = rightLine.getStartPoint().getY();
      int newRightLineY = leftLine.getStartPoint().getY();

      leftLine.setStartPoint(new Point(leftLine.getStartPoint().getX(), newLeftLineY));
      rightLine.setStartPoint(new Point(rightLine.getStartPoint().getX(), newRightLineY));
    }
  }

  @Override
  public void rotate() {
    Line newTopLine = leftLine;
    Line newBottomLine = rightLine;
    Line newLeftLine = bottomLine;
    Line newRightLine = topLine;

    Point newTopLinePoint;
    Point newBottomLinePoint;
    Point newLeftLinePoint;
    Point newRightLinePoint;

    if (leftLine.getLineOrientation().equals(LineOrientation.UPDIAGONAL)
        || leftLine.getLineOrientation().equals(LineOrientation.DOWNDIAGONAL)) {
      newTopLinePoint = startPoint;
      newBottomLinePoint = new Point(startPoint.getX(), startPoint.getY() + width - 1);

      if (leftLine.getLineOrientation().equals(LineOrientation.UPDIAGONAL)) {
        newLeftLinePoint = startPoint;
        newRightLinePoint =
            new Point(startPoint.getX() + height - 1, startPoint.getY() + height - 1);
      } else {
        newLeftLinePoint = new Point(startPoint.getX(), startPoint.getY() + height - 1);
        newRightLinePoint = new Point(startPoint.getX() + height - 1, startPoint.getY());
      }
    } else {
      newLeftLinePoint = startPoint;
      newRightLinePoint = new Point(startPoint.getX() + width - 1, startPoint.getY());

      if (topLine.getLineOrientation().equals(LineOrientation.UPDIAGONAL)) {
        newTopLinePoint = startPoint;
        newBottomLinePoint =
            new Point(startPoint.getX() + height - 1, startPoint.getY() + height - 1);
      } else {
        newTopLinePoint = new Point(startPoint.getX() + height - 1, startPoint.getY());
        newBottomLinePoint = new Point(startPoint.getX(), startPoint.getY() + height - 1);
      }
    }

    topLine = newTopLine;
    bottomLine = newBottomLine;
    leftLine = newLeftLine;
    rightLine = newRightLine;

    topLine.setStartPoint(newTopLinePoint);
    bottomLine.setStartPoint(newBottomLinePoint);
    leftLine.setStartPoint(newLeftLinePoint);
    rightLine.setStartPoint(newRightLinePoint);

    topLine.rotate();
    bottomLine.rotate();
    leftLine.rotate();
    rightLine.rotate();
  }

  @Override
  public String toString() {
    return String.format(
        "%d) Параллелограмм (x: %d, y: %d, ширина: %d, высота: %d)",
        id, startPoint.getX(), startPoint.getY(), width, height);
  }
}
