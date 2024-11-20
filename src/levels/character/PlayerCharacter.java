package levels.character;

import levels.Room;

public class PlayerCharacter extends Room {
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
