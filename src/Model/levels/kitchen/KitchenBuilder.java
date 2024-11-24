package Model.levels.kitchen;

import Model.levels.RoomBuilder;

public class KitchenBuilder implements RoomBuilder<Kitchen> {
    @Override
    public RoomBuilder<Kitchen> chair() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> desk() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> cabinet() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> picture() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> shelves() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> garbage() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> sink() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> toilet() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> shower() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> bath() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> car() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> toolBox() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> fridge() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> freezer() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> couch() {
        return this;
    }

    @Override
    public RoomBuilder<Kitchen> recliner() {
        return this;
    }

    @Override
    public Kitchen build() {
        return new Kitchen();
    }
}
