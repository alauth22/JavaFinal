package Model.levels;

public interface RoomBuilder<T> {

    //Common
    RoomBuilder<T> chair();
    RoomBuilder<T> desk();
    RoomBuilder<T> cabinet();
    RoomBuilder<T> picture();
    RoomBuilder<T> shelves();
    RoomBuilder<T> garbage();
    RoomBuilder<T> sink();

    //Bathroom specific
    RoomBuilder<T> toilet();
    RoomBuilder<T> shower();
    RoomBuilder<T> bath();


    //Garage specific
    RoomBuilder<T> car();
    RoomBuilder<T> toolBox();

    //Kitchen specific
    RoomBuilder<T> fridge();
    RoomBuilder<T> freezer();

    //Living Specific
    RoomBuilder<T> couch();
    RoomBuilder<T> recliner();

    T build();
}
