package Model.levels.bathroom;

import Model.levels.Room;

public class Bath extends Room {
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
        return 'B';
    }
}
