package ru.maxizenit.lab6.dict;

public enum LineOrientation {
  HORIZONTAL("horizontal"),
  VERTICAL("vertical"),
  UPDIAGONAL("updiagonal"),
  DOWNDIAGONAL("downdiagonal");

  private final String name;

  LineOrientation(String name) {
    this.name = name;
  }

  public static LineOrientation parseLineOrientation(String arg) throws Exception {
    if (HORIZONTAL.toString().equals(arg)) {
      return HORIZONTAL;
    } else if (VERTICAL.toString().equals(arg)) {
      return VERTICAL;
    } else if (UPDIAGONAL.toString().equals(arg)) {
      return UPDIAGONAL;
    } else if (DOWNDIAGONAL.toString().equals(arg)) {
      return DOWNDIAGONAL;
    } else {
      throw new Exception();
    }
  }

  public static LineOrientation negative(LineOrientation lineOrientation) {
    return switch (lineOrientation) {
      case HORIZONTAL -> VERTICAL;
      case VERTICAL -> HORIZONTAL;
      case UPDIAGONAL -> DOWNDIAGONAL;
      case DOWNDIAGONAL -> UPDIAGONAL;
    };
  }

  @Override
  public String toString() {
    return name;
  }
}
