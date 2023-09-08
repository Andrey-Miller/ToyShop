package model;

import java.io.*;
import java.util.List;

public class ToyLottery {
    public final Service service;
    private final String path;

    public ToyLottery(String path) {
        service = new Service();
        this.path = path;
    }

    public void read() {
        try {
            File file = new File(path);
            FileReader fileIO = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileIO);
            String toyId = reader.readLine();
            while (toyId != null) {
                String name = reader.readLine();
                String weight = reader.readLine();
                this.service.toLottery(new Toy(Integer.parseInt(toyId), name, Integer.parseInt(weight)));
                toyId = reader.readLine();
            }
            reader.close();
            fileIO.close();
        } catch (Exception e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }
    }

    public void write() {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (int i = 0; i < service.count(); i++) {
                Toy toy = service.getToy(i);
                writer.append(String.format("%s\n", toy.getId()));
                writer.append(String.format("%s\n", toy.getName()));
                writer.append(String.format("%s\n", toy.getWeight()));
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Service Service() {
        return this.service;
    }

    public void saveResult(String pathResult, List<Toy> toysList) {
        try (FileWriter writer = new FileWriter(pathResult, false)) {
            for (Toy toy : toysList) {
                writer.append(String.format("%s  ", toy.getId()));
                writer.append(String.format("%s  ", toy.getName()));
                writer.append(String.format("%s  ", toy.getWeight()));
                writer.append("\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
