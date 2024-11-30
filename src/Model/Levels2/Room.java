package Model.Levels2;

import Model.Levels2.Item;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room {

    //declare variables
    private String name;

    private Boolean isLocked;

    private Boolean lightsOn;

    private Optional<Item> keyRequired;

    private List<RoomObject> roomObjects;

    //constructor setting the baiscs for a typical room
    public Room(String name) {
        //setting the name parameter
        this.name = name;

        //made basic room accessible at all times
        this.isLocked = false;
        this.lightsOn = true;
        this.keyRequired = Optional.empty();
        this.roomObjects = new ArrayList<>();

    }

    //method to lock room
    public void lock(Item key) {
        this.isLocked = true;
        this.keyRequired = Optional.of(key);

    }

    //method to unlock room
    public void unlock(Item key) {
        this.isLocked = false;

        //check if key is required and if we have a key
        if (this.keyRequired.isPresent() && keyRequired.get().equals(key))
        {
            this.isLocked = false;
        }

    }

    //method to turn lights on
    public void toggleLights(Boolean lightsOn)
    {
        this.lightsOn = lightsOn;
    }

    //method to add objects to our arrayList
    public void addObject(RoomObject object)
    {
        roomObjects.add(object);

    }

    //method to enter a room with valdiation
    public String enterRoom(Item playerKey)
    {
        if(!lightsOn)
        {
            return "Too dark to enter the room " + name;
        }

        if(isLocked)
        {
            if(keyRequired.isPresent() && keyRequired.get().equals(playerKey))
            {
                //statement that calls the name (in the constructor with room name)
                return "You unlocked and entered the room of " + name;
            }

            return "The " + name + " is locked";
        }

        return "You have entered the " + name;

    }

    public String searchRoom() {
        StringBuilder result = new StringBuilder("You are searching the " + name + ". ");
        Boolean foundSomething = false;

        for (RoomObject object : roomObjects) {
            //calls the RoomObject search method (currently empty)
            String searchResult = object.search();

            //add to the stringbuilder all the objects that are found in that roomobject
            if (!searchResult.isEmpty()) {
                result.append(searchResult).append("\n");
            }

        }

        return result.toString();

    }

}
