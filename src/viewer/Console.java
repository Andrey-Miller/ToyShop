package viewer;

import model.Toy;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static dataBase.Path.pathToys;

public class Console implements Interface {
    Scanner in;

    public Console() {
        in = new Scanner(System.in);
    }

    @Override
    public void loadMessage() {
        System.out.println("\nМАГАЗИН ИГРУШЕК");
    }

    @Override
    public int getToyId() {
        System.out.print("ID игрушки: ");
        return parseInt(in.nextLine());
    }

    public String getToyName() {
        System.out.print("Название игрушки: ");
        return in.nextLine();
    }

    @Override
    public int getToyDrop() {
        System.out.print("Вес игрушки: ");
        return parseInt(in.nextLine());
    }

    @Override
    public void savedItem() {
        System.out.println("\nИгрушка добавлена");
    }

    @Override
    public int getLotteryCount() {
        System.out.print("Количество розыгрышей: ");
        return parseInt(in.nextLine());
    }

    @Override
    public void lotteryResult(Toy toy) {
        System.out.print("\nРезультаты розыгрыша: ");
        System.out.println(toy);
    }

    @Override
    public void showAll(List<Toy> toys) {
        System.out.println("\nСписок игрушек для розыгрыша:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    @Override
    public void emptyQueue() {
        System.out.println("Нет игрушек для розыгрыша");
    }

    @Override
    public boolean clearQueue() {
        boolean flag = false;
        System.out.print("\nОчистить список игрушек? (да/нет): ");
        if (in.nextLine().equalsIgnoreCase("да")) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void savedAll() {
        System.out.println("\nСписок игрушек сохранен в файл: " + pathToys);
    }

    @Override
    public int update() {
        return 0;
    }
}

