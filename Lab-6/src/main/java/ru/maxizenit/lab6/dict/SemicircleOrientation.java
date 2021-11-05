package ru.maxizenit.lab6.dict;

public enum SemicircleOrientation {
  UP("up"),
  DOWN("down"),
  LEFT("left"),
  RIGHT("right");

  private final String name;

  SemicircleOrientation(String name) {
    this.name = name;
  }

  public static SemicircleOrientation parseSemicircleOrientation(String arg) throws Exception {
    if (UP.toString().equals(arg)) {
      return UP;
    } else if (DOWN.toString().equals(arg)) {
      return DOWN;
    } else if (LEFT.toString().equals(arg)) {
      return LEFT;
    } else if (RIGHT.toString().equals(arg)) {
      return RIGHT;
    } else {
      throw new Exception();
    }
  }

  public static SemicircleOrientation negative(SemicircleOrientation semicircleOrientation) {
    return switch (semicircleOrientation) {
      case UP -> DOWN;
      case DOWN -> UP;
      case LEFT -> RIGHT;
      case RIGHT -> LEFT;
    };
  }

  @Override
  public String toString() {
    return name;
  }
}
