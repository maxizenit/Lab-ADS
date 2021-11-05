package ru.maxizenit.lab5.util.consts;

public class Messages {

  public static final String INPUT_FIELD_SIZE_MESSAGE =
      "Введите ширину и высоту поля для рисования: ";
  public static final String INPUT_DRAW_AND_EMPTY_SYMBOL_MESSAGE =
      "Введите неразрывно символ заливки и символ пустоты: ";
  public static final String INPUT_COMMAND_MESSAGE = "Введите команду: ";
  public static final String INPUT_ID_MESSAGE = "Введите id фигуры: ";
  public static final String INPUT_SHAPE_TITLE_MESSAGE =
      "Введите название фигуры (help - список фигур): ";

  public static final String NOT_FOUND_COMMAND_MESSAGE = "Введённой команды не существует!";
  public static final String NOT_FOUND_SHAPE_TITLE_MESSAGE = "Нет фигуры с таким названием!";
  public static final String SHAPE_NOT_ROTATABLE_MESSAGE = "Данную фигуру нельзя повернуть!";
  public static final String SHAPE_NOT_REFLECTABLE_MESSAGE = "Данную фигуру нельзя отразить!";

  public static final String LINE_INPUT_MESSAGE = "Введите x, y, длину, ориентацию линии: ";
  public static final String TETRAGON_INPUT_MESSAGE = "Введите x, y, ширину, высоту: ";
  public static final String SQUARE_INPUT_MESSAGE = "Введите x, y, длину стороны: ";
  public static final String CIRCLE_INPUT_MESSAGE = "Введите x, y, радиус: ";
  public static final String SEMICIRCLE_INPUT_MESSAGE =
      "Введите x, y, радиус, ориентацию полукруга: ";
  public static final String PLAIN_CROSS_INPUT_MESSAGE = "Введите x, y, ширину, высоту: ";

  public static final String HELP_MESSAGE =
      "Список команд:\n"
          + "pic - нарисовать предварительно подготовленное изображение\n"
          + "add - добавить фигуру\n"
          + "remove - удалить фигуру\n"
          + "print - вывести список фигур\n"
          + "show - показать поле\n"
          + "rotate - повернуть фигуру на 90 градусов\n"
          + "reflect - отразить фигуру\n"
          + "help - помощь\n"
          + "exit - выход\n"
          + "Ориентации линий:\n"
          + "horizontal (-)\n"
          + "vertical (|)\n"
          + "updiagonal(/)\n"
          + "downdiagonal (\\)\n"
          + "Ориентации полуокружностей:\n"
          + "up (вверх)\n"
          + "down (вниз)\n"
          + "left (влево)\n"
          + "right (вправо)";
}
