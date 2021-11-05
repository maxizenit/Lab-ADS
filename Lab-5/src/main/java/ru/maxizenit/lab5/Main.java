package ru.maxizenit.lab5;

import ru.maxizenit.lab5.service.CommandConsumer;

public class Main {

  public static void main(String[] args) throws Exception {
    try (CommandConsumer consumer = new CommandConsumer(System.in, System.out)) {
      consumer.start();
    }
  }
}
