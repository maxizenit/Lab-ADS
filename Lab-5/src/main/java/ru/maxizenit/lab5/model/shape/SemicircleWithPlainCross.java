package ru.maxizenit.lab5.model.shape;

import ru.maxizenit.lab5.dict.SemicircleOrientation;
import ru.maxizenit.lab5.model.field.Field;

public class SemicircleWithPlainCross extends SemicircleWithCross {

  public SemicircleWithPlainCross(
      Field field, Point startPoint, int radius, SemicircleOrientation semicircleOrientation) {
    super(field, startPoint, radius, semicircleOrientation);

    int width;
    int height;

    if (this.semicircleOrientation.equals(SemicircleOrientation.UP)
        || this.semicircleOrientation.equals(SemicircleOrientation.DOWN)) {
      width = radius * 2 + 1;
      height = radius;
    } else {
      width = radius;
      height = radius * 2 + 1;
    }

    cross = new PlainCross(field, startPoint, width, height);
  }
}
