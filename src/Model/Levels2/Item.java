package Model.Levels2;

public class Item {

    private String name;

    public Item(String name)
    {
        this.name = name;

    }

    public String getName() {

        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.equals(obj))
        {
            return true;
        }
        if(obj == null || !getClass().equals(obj.getClass()))
        {
            return false;

        }

        //casting item to obj even though the datatypes differ
        Item item = (Item) obj;
        return name.equalsIgnoreCase(item.name);

    }

}
