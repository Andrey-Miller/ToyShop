package viewer;

import model.Toy;

import java.util.List;

public interface Interface {
    int getToyId();
    String getToyName();
    int getToyDrop();
    void showAll(List<Toy> toys);
    boolean clearQueue();
    void savedAll();
    void savedItem();
    void emptyQueue();
    void lotteryResult(Toy toy);
    void loadMessage();
    int getLotteryCount();
    int update();
}
