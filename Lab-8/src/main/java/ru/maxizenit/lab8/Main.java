package ru.maxizenit.lab8;

import ru.maxizenit.lab8.tree.Tree;
import ru.maxizenit.lab8.util.SetGenerator;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

  private static final String fileName = "in.txt";
  private static final String startSizeMessage = "Введите стартовый размер множеств";
  private static final String endSizeMessage = "Введите конечный размер множеств";
  private static final String stepMessage = "Введите величину прибавления размера";

  private static Tree<Character> a, b, c, d, e;
  private static SetGenerator setGenerator = new SetGenerator();

  public static void main(String[] args) throws IOException {
    if (!Files.exists(Path.of(fileName))) {
      Files.createFile(Path.of(fileName));
    }

    FileWriter fw = new FileWriter(fileName);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    long start, end;
    double elapsed;
    int startSize, endSize, step;

    System.out.printf("%s: ", startSizeMessage);
    startSize = Integer.parseInt(reader.readLine());

    System.out.printf("%s: ", endSizeMessage);
    endSize = Integer.parseInt(reader.readLine());

    System.out.printf("%s: ", stepMessage);
    step = Integer.parseInt(reader.readLine());

    for (int i = startSize; i <= endSize; i += step) {
      a = setGenerator.generate(i);
      b = setGenerator.generate(i);
      c = setGenerator.generate(i);
      d = setGenerator.generate(i);
      e = setGenerator.generate(i);

      start = System.nanoTime();
      a.union(b).union(c).symmetricDifference(d).intersection(e);
      end = System.nanoTime();

      elapsed = (double) (end - start) / 1_000_000;

      System.out.printf("Мощность %d: %f мс%n", i, elapsed);
      fw.write(String.format("%d\t%d%n", i, Math.round(elapsed)));
    }

    reader.close();
    fw.close();
  }
}
