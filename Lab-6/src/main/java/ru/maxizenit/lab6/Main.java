package ru.maxizenit.lab6;

import ru.maxizenit.lab6.service.CommandConsumer;

public class Main {

  public static void main(String[] args) throws Exception {
    try (CommandConsumer consumer = new CommandConsumer(System.in, System.out, System.out)) {
      consumer.start();
    }
  }
}
