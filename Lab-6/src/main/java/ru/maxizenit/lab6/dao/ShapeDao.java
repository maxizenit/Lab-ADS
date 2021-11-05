package ru.maxizenit.lab6.dao;

import ru.maxizenit.lab6.model.shape.Shape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapeDao {

  private final Map<Integer, Shape> shapes;
  private int currentId;

  public ShapeDao() {
    shapes = new HashMap<>();
    currentId = 0;
  }

  public void add(Shape shape) {
    shape.setId(currentId++);
    shapes.put(shape.getId(), shape);
  }

  public Shape get(int id) {
    return shapes.get(id);
  }

  public void remove(int id) {
    shapes.remove(id);
  }

  public List<Shape> getAllShapes() {
    return shapes.values().stream().toList();
  }
}
