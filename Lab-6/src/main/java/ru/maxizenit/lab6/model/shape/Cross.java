package ru.maxizenit.lab6.model.shape;

import ru.maxizenit.lab6.model.field.Field;

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
