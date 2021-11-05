package ru.maxizenit.lab6.util;

import ru.maxizenit.lab6.exception.WrongArgumentsException;
import ru.maxizenit.lab6.util.consts.ErrorMessages;

import java.io.PrintStream;

public class ExceptionHandler {

  private final PrintStream printStream;

  public ExceptionHandler(PrintStream printStream) {
    this.printStream = printStream;
  }

  public void handle(Exception exception) {
    if (exception instanceof WrongArgumentsException) {
      handleWrongArgumentsException((WrongArgumentsException) exception);
    }
  }

  private void handleWrongArgumentsException(WrongArgumentsException exception) {
    printStream.println(ErrorMessages.WRONG_ARGUMENTS);
  }
}
