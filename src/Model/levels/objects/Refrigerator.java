package Model.levels.objects;

import Model.levels.Item;
import Model.levels.RoomObject;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator implements RoomObject {

    String name;
    private List<Item> items;

    public Refrigerator(String name)
    {
        this.items = new ArrayList<>();
        this.name = name;
    }
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
