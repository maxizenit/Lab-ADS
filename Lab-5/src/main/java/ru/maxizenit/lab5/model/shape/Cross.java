package ru.maxizenit.lab5.model.shape;

import ru.maxizenit.lab5.model.field.Field;

public abstract class Cross extends Shape {

  protected Line firstLine;
  protected Line secondLine;

  public Cross(Field field, Point startPoint) {
    super(field, startPoint);
  }

  @Override
  public void draw() {
    firstLine.draw();
    secondLine.draw();
  }
}
