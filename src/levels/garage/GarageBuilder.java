package levels.garage;

import levels.RoomBuilder;

public class GarageBuilder implements RoomBuilder<Garage> {
    @Override
    public RoomBuilder<Garage> chair() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> desk() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> cabinet() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> picture() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> shelves() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> garbage() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> sink() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> toilet() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> shower() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> bath() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> car() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> toolBox() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> fridge() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> freezer() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> couch() {
        return this;
    }

    @Override
    public RoomBuilder<Garage> recliner() {
        return this;
    }

    @Override
    public Garage build() {
        return new Garage();
    }
}
