package Model.levels.hallway;

import Model.levels.RoomBuilder;

public class HallwayBuilder implements RoomBuilder<Hallway> {
    @Override
    public RoomBuilder<Hallway> chair() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> desk() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> cabinet() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> picture() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> shelves() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> garbage() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> sink() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> toilet() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> shower() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> bath() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> car() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> toolBox() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> fridge() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> freezer() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> couch() {
        return this;
    }

    @Override
    public RoomBuilder<Hallway> recliner() {
        return this;
    }

    @Override
    public Hallway build() {
        return new Hallway();
    }
}
