package Model.levels.room;

import Model.levels.Item;

import java.util.List;

public interface RoomObject {

    //search method
    String search();
    String getName();
    void addItem(Item item);
    String removeItem(String itemName);
    List<Item> getItems();

    char getSymbol();
}
