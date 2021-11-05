package ru.maxizenit.lab7;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharWithIndex implements Comparable<CharWithIndex> {

  private Character character;
  private Integer index;

  public CharWithIndex(char character, int index) {
    this.character = character;
    this.index = index;
  }

  @Override
  public int compareTo(CharWithIndex o) {
    return index.compareTo(o.index);
  }

  @Override
  public String toString() {
    return character.toString();
  }
}
