package ru.maxizenit.lab6.service;

import ru.maxizenit.lab6.dao.ShapeDao;
import ru.maxizenit.lab6.exception.WrongArgumentsException;
import ru.maxizenit.lab6.model.field.Field;
import ru.maxizenit.lab6.model.shape.Reflectable;
import ru.maxizenit.lab6.model.shape.Rotatable;
import ru.maxizenit.lab6.model.shape.Shape;
import ru.maxizenit.lab6.util.ExceptionHandler;
import ru.maxizenit.lab6.util.PresetImage;
import ru.maxizenit.lab6.util.consts.Commands;
import ru.maxizenit.lab6.util.consts.Messages;
import ru.maxizenit.lab6.util.ShapeFactory;
import ru.maxizenit.lab6.util.consts.ShapeTitles;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandConsumer implements AutoCloseable {

  private final BufferedReader reader;
  private final PrintStream printStream;

  private final Field field;
  private final ShapeDao dao;
  private final ShapeFactory factory;
  private final ExceptionHandler exceptionHandler;

  public CommandConsumer(
      InputStream inputStream, PrintStream printStream, PrintStream errorStream) throws IOException {
    this.printStream = printStream;

    exceptionHandler = new ExceptionHandler(errorStream);
    dao = new ShapeDao();
    reader = new BufferedReader(new InputStreamReader(inputStream));
    field = createField();
    factory = new ShapeFactory(field);
  }

  private Field createField() throws IOException {
    String[] args;
    char[] symbols;
    int width = 0;
    int height = 0;
    char drawSymbol = ' ';
    char emptySymbol = ' ';

    boolean isException = true;

    do {
      printStream.print(Messages.INPUT_FIELD_SIZE_MESSAGE);
      args = reader.readLine().split(" ");

      try {
        if (args.length < 2) {
          throw new WrongArgumentsException();
        }

        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);

        if (width <= 0 || height <= 0) {
          throw new WrongArgumentsException();
        }

        isException = false;
      } catch (WrongArgumentsException e) {
        exceptionHandler.handle(e);
      }
    }
    while (isException);

    isException = true;

    do {
      printStream.print(Messages.INPUT_DRAW_AND_EMPTY_SYMBOL_MESSAGE);
      symbols = reader.readLine().toCharArray();

      try {
        if (symbols.length < 2) {
          throw new WrongArgumentsException();
        }

        drawSymbol = symbols[0];
        emptySymbol = symbols[1];
        isException = false;
      } catch (Exception e) {
        exceptionHandler.handle(e);
      }
    } while (isException);

    return new Field(width, height, drawSymbol, emptySymbol);
  }

  public void start() throws Exception {
    while (true) {
      printStream.print(Messages.INPUT_COMMAND_MESSAGE);
      String command = reader.readLine();

      if (command.equals(Commands.PIC_COMMAND)) {
        pic();
      } else if (command.equals(Commands.ADD_COMMAND)) {
        add();
      } else if (command.equals(Commands.REMOVE_COMMAND)) {
        remove();
      } else if (command.equals(Commands.PRINT_COMMAND)) {
        print();
      } else if (command.equals(Commands.SHOW_COMMAND)) {
        show();
      } else if (command.equals(Commands.ROTATE_COMMAND)) {
        rotate();
      } else if (command.equals(Commands.REFLECT_COMMAND)) {
        reflect();
      } else if (command.equals(Commands.HELP_COMMAND)) {
        help();
      } else if (command.equals(Commands.EXIT_COMMAND)) {
        break;
      } else {
        printStream.println(Messages.NOT_FOUND_COMMAND_MESSAGE);
      }
    }

    close();
  }

  @Override
  public void close() throws Exception {
    reader.close();
  }

  private void pic() {
    PresetImage.show(printStream);
  }

  private void add() throws Exception {
    Shape newShape = null;
    String shapeTitle;

    printStream.print(Messages.INPUT_SHAPE_TITLE_MESSAGE);
    shapeTitle = reader.readLine();

    switch (shapeTitle) {
      case Commands.HELP_COMMAND -> printStream.println(ShapeTitles.SHAPE_TITLES);
      case ShapeTitles.LINE_TITLE -> {
        printStream.print(Messages.LINE_INPUT_MESSAGE);
        newShape = factory.createLine(reader.readLine().split(" "));
      }
      case ShapeTitles.RECTANGLE_TITLE -> {
        printStream.print(Messages.TETRAGON_INPUT_MESSAGE);
        newShape = factory.createRectangle(reader.readLine().split(" "));
      }
      case ShapeTitles.SQUARE_TITLE -> {
        printStream.print(Messages.SQUARE_INPUT_MESSAGE);
        newShape = factory.createSquare(reader.readLine().split(" "));
      }
      case ShapeTitles.PARALLELOGRAM_TITLE -> {
        printStream.print(Messages.TETRAGON_INPUT_MESSAGE);
        newShape = factory.createParallelogram(reader.readLine().split(" "));
      }
      case ShapeTitles.CIRCLE_TITLE -> {
        printStream.print(Messages.CIRCLE_INPUT_MESSAGE);
        newShape = factory.createCircle(reader.readLine().split(" "));
      }
      case ShapeTitles.SEMICIRCLE_TITLE -> {
        printStream.print(Messages.SEMICIRCLE_INPUT_MESSAGE);
        newShape = factory.createSemicircle(reader.readLine().split(" "));
      }
      case ShapeTitles.SEMICIRCLE_WITH_PLAIN_CROSS_TITLE -> {
        printStream.print(Messages.SEMICIRCLE_INPUT_MESSAGE);
        newShape = factory.createSemicircleWithPlainCross(reader.readLine().split(" "));
      }
      case ShapeTitles.PLAIN_CROSS_TITLE -> {
        printStream.print(Messages.PLAIN_CROSS_INPUT_MESSAGE);
        newShape = factory.createPlainCross(reader.readLine().split(" "));
      }
      default -> printStream.println(Messages.NOT_FOUND_SHAPE_TITLE_MESSAGE);
    }

    if (newShape != null) {
      dao.add(newShape);
      updateField();
    }
  }

  private void remove() throws IOException {
    printStream.print(Messages.INPUT_ID_MESSAGE);
    int id = Integer.parseInt(reader.readLine());

    dao.remove(id);
    updateField();
  }

  private void print() {
    dao.getAllShapes().forEach(printStream::println);
  }

  private void show() {
    field.show(printStream);
  }

  private void rotate() throws IOException {
    printStream.print(Messages.INPUT_ID_MESSAGE);

    Shape shape = dao.get(Integer.parseInt(reader.readLine()));

    if (shape instanceof Rotatable) {
      ((Rotatable) shape).rotate();
      updateField();
    } else {
      printStream.print(Messages.SHAPE_NOT_ROTATABLE_MESSAGE);
    }
  }

  private void reflect() throws IOException {
    printStream.print(Messages.INPUT_ID_MESSAGE);

    Shape shape = dao.get(Integer.parseInt(reader.readLine()));

    if (shape instanceof Reflectable) {
      ((Reflectable) shape).reflect();
      updateField();
    } else {
      printStream.print(Messages.SHAPE_NOT_REFLECTABLE_MESSAGE);
    }
  }

  private void help() {
    printStream.println(Messages.HELP_MESSAGE);
  }

  private void updateField() {
    field.clear();
    dao.getAllShapes().forEach(Shape::draw);
  }
}
