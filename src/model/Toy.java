package model;

public class Toy implements Comparable<Toy> {
    public int id;
    public String name;
    public int weight;

    public Toy(int id, String name, int drop) {
        this.id = id;
        this.name = name;
        this.weight = drop;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ID:" + id + "; Игрушка: " + name + ";  Шанс выпадения: " + weight;
    }

    @Override
    public int compareTo(Toy o) {
        return o.getWeight() - this.getWeight();
    }
}

