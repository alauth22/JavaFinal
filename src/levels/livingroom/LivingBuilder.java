package levels.livingroom;

import levels.RoomBuilder;

public class LivingBuilder implements RoomBuilder<Living> {
    @Override
    public RoomBuilder<Living> chair() {
        return this;
    }

    @Override
    public RoomBuilder<Living> desk() {
        return this;
    }

    @Override
    public RoomBuilder<Living> cabinet() {
        return this;
    }

    @Override
    public RoomBuilder<Living> picture() {
        return this;
    }

    @Override
    public RoomBuilder<Living> shelves() {
        return this;
    }

    @Override
    public RoomBuilder<Living> garbage() {
        return this;
    }

    @Override
    public RoomBuilder<Living> sink() {
        return this;
    }

    @Override
    public RoomBuilder<Living> toilet() {
        return this;
    }

    @Override
    public RoomBuilder<Living> shower() {
        return this;
    }

    @Override
    public RoomBuilder<Living> bath() {
        return this;
    }

    @Override
    public RoomBuilder<Living> car() {
        return this;
    }

    @Override
    public RoomBuilder<Living> toolBox() {
        return this;
    }

    @Override
    public RoomBuilder<Living> fridge() {
        return this;
    }

    @Override
    public RoomBuilder<Living> freezer() {
        return this;
    }

    @Override
    public RoomBuilder<Living> couch() {
        return this;
    }

    @Override
    public RoomBuilder<Living> recliner() {
        return this;
    }

    @Override
    public Living build() {
        return new Living();
    }
}
