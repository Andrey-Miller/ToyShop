package presenter;

import model.ToyLottery;
import model.Toy;
import viewer.Interface;
import dataBase.Path;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Presenter {
    private final ToyLottery lottery;
    private final Interface view;


    public Presenter(Interface view, String path) {
        this.view = view;
        lottery = new ToyLottery(path);
    }

    public void addToy() {
        if (lottery.Service().toLottery(
                new Toy (view.getToyId(), view.getToyName(), view.getToyDrop() ) ))
            view.savedItem();
    }

    public void deleteToy() {
        if (lottery.service.getToys().size() == 0)
            view.emptyQueue();
        else
            lottery.Service().remove(view.getToyId());
    }

    public void Lottery() {
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>();
        Toy lotteryToy;
        List<Toy> lotteryResult = new ArrayList<>();
        if (lottery.service.getToys().size() != 0) {
            int times = view.getLotteryCount();
            priorityQueue.addAll(lottery.Service().getToys());
            while (priorityQueue.size() < times) {
                priorityQueue.addAll(lottery.Service().getToys());
            }
            for (int i = 0; i < times; i++) {
                lotteryToy = priorityQueue.remove();
                view.lotteryResult(lotteryToy);
                lotteryResult.add (lotteryToy);
            }
            lottery.saveResult (Path.pathResult, lotteryResult );
        } else
            view.emptyQueue();
    }

    public void showAll() {
        if (lottery.service.getToys().size() == 0)
            view.emptyQueue();
        else
            view.showAll (lottery.service.getToys());
    }

    public void clearAll() {
        if (lottery.service.getToys().size() == 0)
            view.emptyQueue();
        else {
            if (view.clearQueue()) {
                lottery.service.getToys().clear();
                System.out.println ("Спискок игрушек очищен");
            }
        }
    }

    public void saveToFile() {
        lottery.write();
        view.savedAll();
    }

    public void loadFromFile() {
        lottery.read();
        view.loadMessage();
    }

    public int update(Scanner scanner, Collection<? extends Toy> toys, String pathDb) {
        System.out.print ( "Введите ID игрушки: " );
        int toyId = scanner.nextInt ();
        scanner.nextLine ();
        System.out.print ( "Введите вес игрушки: " );
        String newWeightStr = scanner.nextLine();
        int newWeight;
        try {
            newWeight = Integer.parseInt(newWeightStr);
        } catch (NumberFormatException e) {
            System.out.println("Введено некорректное значение");
            return 0;
        }
        if (lottery.service.getToys() != null) {
            for (Toy toy : lottery.service.getToys()) {
                if (toy.getId() == toyId) {
                    toy.setWeight(newWeight);
                    break;
                }
            }
        } else {
            List<Toy> toyList = new ArrayList<>(toys);
            lottery.service.setToys(toyList);
        }
        try (FileWriter writer = new FileWriter(pathDb, false)) {
            for (Toy toy : lottery.service.getToys()) {
                writer.append(String.format("%d\n", toy.getId()));
                writer.append(String.format("%s\n", toy.getName()));
                writer.append(String.format("%d\n", toy.getWeight()));
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        view.update ();
        System.out.println ( "Вес игрушки изменен" );
        return toyId;
    }
}


