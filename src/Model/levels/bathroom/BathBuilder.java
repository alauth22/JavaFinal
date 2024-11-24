package Model.levels.bathroom;

import Model.levels.RoomBuilder;

public class BathBuilder  implements RoomBuilder<Bath> {

    @Override
    public RoomBuilder<Bath> chair() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> desk() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> cabinet() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> picture() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> shelves() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> garbage() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> toilet() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> shower() {
        return this;
    }
    @Override
    public RoomBuilder<Bath> sink() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> bath() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> car() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> toolBox() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> fridge() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> freezer() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> couch() {
        return this;
    }

    @Override
    public RoomBuilder<Bath> recliner() {
        return this;
    }

    @Override
    public Bath build() {
        return new Bath();
    }

}
