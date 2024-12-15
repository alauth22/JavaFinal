package Model.levels.room;

import Model.levels.Item;
import java.util.ArrayList;
import java.util.List;

//the class that will implement our RoomObject interface.
public class RoomObjects implements RoomObject {

    private String name;
    private List<Item> items;

    //constructor
    public RoomObjects(String name)
    {
        this.items = new ArrayList<>();
        this.name = name;
    }

    //search for the items in the room or inside a room object.
    @Override
    public String search() {
        if(items.isEmpty())
        {
            return "The " + name + "is empty.";
        }

        StringBuilder result = new StringBuilder("Inside the " + name + ", you find: ");

        for(Item item : items)
        {
            result.append(item.getName() + "\n");
        }
        return result.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    //allows us to remove an item from the ArrayList when player takes that item.
    @Override
    public String removeItem(String itemName) {
        for(Item item : items)
        {
            if(item.getName().equalsIgnoreCase(itemName))
            {
                items.remove(item);
                return "You took the " + itemName + "from the " + name + ".";
            }

        }
        return "There is no " + itemName + " in the " + name + ".";
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public char getSymbol() {
        return name.charAt(0);
    }
}
