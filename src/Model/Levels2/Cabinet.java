package Model.Levels2;

import java.util.ArrayList;
import java.util.List;

public class Cabinet implements RoomObject{

    private List<Item> items;


    public Cabinet()
    {
        this.items = new ArrayList<>();

    }


    public Cabinet addItem(Item item)
    {
        items.add(item);
        return this;
    }


    @Override
    public String search() {

        if(items.isEmpty())
        {
            return "The Cabinet is Empty!";
        }

        StringBuilder result = new StringBuilder("You have found the following in the cabinet." + "\n");

        for (Item item : items)
        {
            result.append(item.getName() + "\n");
        }

        //items.clear();

        return result.toString();
    }
}
