package ru.maxizenit.lab6.model.shape;

import lombok.Getter;
import lombok.Setter;
import ru.maxizenit.lab6.model.field.Field;

@Getter
@Setter
public abstract class Shape {

  protected int id;
  protected Field field;
  protected Point startPoint;

  public Shape(Field field, Point startPoint) {
    this.field = field;
    this.startPoint = startPoint;
  }

  public abstract void draw();
}
