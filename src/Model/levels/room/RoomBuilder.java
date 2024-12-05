package Model.levels.room;

import Model.levels.Item;

public class RoomBuilder {

    //initialized that
    private Room room;


    //constructor
    public RoomBuilder(String name)
    {
        room = new Room(name);
    }



    public RoomBuilder setLocked(Item key) {
        room.lock(key);
        return this;
    }


    public RoomBuilder setLightsOn(Boolean lightsOn) {
        room.toggleLights(lightsOn);
        return this;
    }


    public RoomBuilder addObject(RoomObject object) {
        room.addObject(object);
        return this;
    }



    public Room build() {
        return room;
    }



}
