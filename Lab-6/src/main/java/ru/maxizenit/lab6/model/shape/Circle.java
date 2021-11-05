package ru.maxizenit.lab6.model.shape;

import lombok.Getter;
import ru.maxizenit.lab6.model.field.Field;

public class Circle extends Shape {

  @Getter protected int radius;
  protected Point center;

  public Circle(Field field, Point startPoint, int radius) {
    super(field, startPoint);
    this.radius = radius;
    initializeCenter();
  }

  public void setRadius(int radius) {
    this.radius = radius;
    initializeCenter();
  }

  private void initializeCenter() {
    center = new Point(startPoint.getX() + radius, startPoint.getY() + radius);
  }

  @Override
  public void draw() {
    for (int i = 1; i < 360; ++i) {
      double angle = (double) i / 180 * Math.PI;
      int x = (int) Math.round(center.getX() + radius * Math.cos(angle));
      int y = (int) Math.round(center.getY() + radius * Math.sin(angle));

      if (x < 0 || x >= field.getWidth()) {
        continue;
      }
      if (y < 0 || y >= field.getHeight()) {
        continue;
      }

      StringBuilder sb = new StringBuilder(field.getMatrix()[y]);
      sb.setCharAt(x, field.getDrawSymbol());
      field.getMatrix()[y] = sb.toString();
    }
  }
}
