package Model.levels.character;

import Model.levels.Room;

public class Character extends Room {
    @Override
    public boolean lights(boolean onOff) {
        return false;
    }

    @Override
    public boolean doorsLocked(boolean lockedUnlock) {
        return false;
    }

    @Override
    public boolean windowsLocked(boolean lockedUnlock) {
        return false;
    }

    @Override
    public char getRoomSymbol() {
        return 'P';
    }
}
