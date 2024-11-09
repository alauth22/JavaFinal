package levels;

abstract public class Room {
    public abstract boolean lights(boolean onOff);
    public abstract boolean doorsLocked(boolean lockedUnlock);
    public abstract boolean windowsLocked(boolean lockedUnlock);

    public abstract char getRoomSymbol();
}
