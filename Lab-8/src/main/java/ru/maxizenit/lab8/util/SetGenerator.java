package ru.maxizenit.lab8.util;

import ru.maxizenit.lab8.tree.Tree;

public class SetGenerator {

  public Tree<Character> generate(int power) {
    CharGenerator charGenerator = new CharGenerator((int) System.currentTimeMillis());
    Tree<Character> result = new Tree<>();

    for (int i = 0; i < power; ++i) {
      char newChar;

      do {
        newChar = charGenerator.generate();
      } while (result.contains(newChar));

      result.add(newChar);
    }

    return result;
  }
}
