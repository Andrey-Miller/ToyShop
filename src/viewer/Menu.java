package viewer;

import presenter.Presenter;
import model.Toy;
import dataBase.Path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Menu {
    protected static Collection<? extends Toy> toyNewWeight = new ArrayList<>();
    protected static Collection<? extends Toy> toyNewQuantity = new ArrayList<> ();

    public static void userConsole() {
        Presenter presenter = new Presenter(new Console(), Path.pathToys);
        Scanner scanner = new Scanner (System.in);
        presenter.loadFromFile();

        String command;
        while (true) {

            command = prompt("""

                     1 - Добавить игрушку в список
                     2 - Удалить игрушку из списка
                     3 - Начать розыгрышь
                     4 - Вывести список игрушек
                     5 - Очистить список игрушек
                     6 - Сохранить список игрушек в файл 
                     7 - Загрузить список игрушек из файла
                     8 - Изменить вес игрушки
                     0 - Выход
                     
                    Введите команду:\s""");
            if (command.equals("0")) {
                System.out.println("\nПрограмма завешена");
                return;
            }
            try {
                switch (command) {
                    case "1" -> presenter.addToy();
                    case "2" -> presenter.deleteToy();
                    case "3" -> presenter.Lottery();
                    case "4" -> presenter.showAll();
                    case "5" -> presenter.clearAll();
                    case "6" -> presenter.saveToFile();
                    case "7" -> presenter.loadFromFile();
                    case "8" -> presenter.update(scanner, toyNewWeight, toyNewQuantity.toString () );
                    default -> System.out.println("\nКоманда не найдена!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка. " + e.getMessage());
            }
        }
    }

    private static String prompt(String message) {
        Scanner in = new Scanner( System.in );
        System.out.print(message);
        return in.nextLine();
    }

}

