package ru.maxizenit.lab7;

import ru.maxizenit.lab7.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static final String setsCommand = "sets";
  private static final String concatCommand = "concat";
  private static final String exclCommand = "excl";
  private static final String substCommand = "subst";
  private static final String helpCommand = "help";
  private static final String exitCommand = "exit";
  private static final String inputCommandMessage = "Введите команду: ";
  private static final String inputSubstPositionMessage = "Введите p: ";
  private static final String inputSetMessage = "Введите множество";
  private static final String outputSetMessage = "Множество";
  private static final String wrongCommandMessage = "Неверная команда!";
  private static final String helpMessage =
      "Список команд:\n"
          + "sets - выполнить заданную операцию над множествами\n"
          + "concat - выполнить операцию concat\n"
          + "excl - выполнить операцию excl\n"
          + "subst - выполнить операцию subst\n"
          + "exit - выйти из программы";

  private static BufferedReader reader;

  public static void main(String[] args) throws IOException {
    reader = new BufferedReader(new InputStreamReader(System.in));
    String command;

    while (true) {
      System.out.print(inputCommandMessage);
      command = reader.readLine();

      if (setsCommand.equals(command)) {
        operation();
      } else if (concatCommand.equals(command)) {
        concat();
      } else if (exclCommand.equals(command)) {
        excl();
      } else if (substCommand.equals(command)) {
        subst();
      } else if (helpCommand.equals(command)) {
        System.out.println(helpMessage);
      } else if (exitCommand.equals(command)) {
        break;
      } else {
        System.out.println(wrongCommandMessage);
      }
    }

    reader.close();
  }

  private static Tree<Character> inputTree(char tag) throws IOException {
    System.out.printf("%s %c: ", inputSetMessage, tag);

    String set = reader.readLine();
    Tree<Character> tree = new Tree<>();

    for (int i = 0; i < set.length(); ++i) {
      tree.add(set.charAt(i));
    }

    return tree;
  }

  private static Tree<CharWithIndex> inputTreeWithDuplicates(char tag) throws IOException {
    System.out.printf("%s %c: ", inputSetMessage, tag);

    String set = reader.readLine();
    Tree<CharWithIndex> tree = new Tree<>();

    for (int i = 0; i < set.length(); ++i) {
      tree.add(new CharWithIndex(set.charAt(i), i));
    }

    return tree;
  }

  private static void operation() throws IOException {
    Tree<Character> a = inputTree('A');
    Tree<Character> b = inputTree('B');
    Tree<Character> c = inputTree('C');
    Tree<Character> d = inputTree('D');
    Tree<Character> e = inputTree('E');

    Tree<Character> f = a.union(b).union(c).symmetricDifference(d).intersection(e);

    System.out.printf("%s %c: %s%n", outputSetMessage, 'A', a);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'B', b);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'C', c);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'D', d);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'E', e);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'F', f);
  }

  private static void concat() throws IOException {
    Tree<CharWithIndex> a = inputTreeWithDuplicates('A');
    Tree<CharWithIndex> b = inputTreeWithDuplicates('B');

    String c = String.valueOf(a) + b;

    System.out.printf("%s %c: %s%n", outputSetMessage, 'A', a);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'B', b);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'C', c);
  }

  private static void excl() throws IOException {
    Tree<CharWithIndex> a = inputTreeWithDuplicates('A');
    Tree<CharWithIndex> b = inputTreeWithDuplicates('B');

    String c = String.valueOf(a).replaceAll(String.valueOf(b), "");

    System.out.printf("%s %c: %s%n", outputSetMessage, 'A', a);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'B', b);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'C', c);
  }

  private static void subst() throws IOException {
    System.out.print(inputSubstPositionMessage);
    int p = Integer.parseInt(reader.readLine());

    Tree<CharWithIndex> a = inputTreeWithDuplicates('A');
    Tree<CharWithIndex> b = inputTreeWithDuplicates('B');

    String c = String.valueOf(a).substring(0, p) + b + String.valueOf(a).substring(p);

    System.out.printf("%s %c: %s%n", outputSetMessage, 'A', a);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'B', b);
    System.out.printf("%s %c: %s%n", outputSetMessage, 'C', c);
  }
}
