package ru.maxizenit.lab6.model.shape;

import ru.maxizenit.lab6.dict.SemicircleOrientation;
import ru.maxizenit.lab6.model.field.Field;

public abstract class SemicircleWithCross extends Semicircle {

  protected Cross cross;

  public SemicircleWithCross(
      Field field, Point startPoint, int radius, SemicircleOrientation semicircleOrientation) {
    super(field, startPoint, radius, semicircleOrientation);
  }

  @Override
  public void draw() {
    super.draw();
    cross.draw();
  }
}
