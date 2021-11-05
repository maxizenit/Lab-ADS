package ru.maxizenit.lab8.util;

import java.util.Random;

public class CharGenerator {

  private static final int MAX_CHAR = 65536;

  private final Random random;

  public CharGenerator(int seed) {
    random = new Random(seed);
  }

  public char generate() {
    return (char) random.nextInt(MAX_CHAR);
  }
}
