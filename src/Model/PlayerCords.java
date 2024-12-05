package Model;

import Model.levels.LevelGridSystem;

public class PlayerCords {

    private int coordX;
    private int coordY;
    private LevelGridSystem levelGridSystem;

    public PlayerCords(LevelGridSystem levelGridSystem, int startX, int startY) {
        this.levelGridSystem = levelGridSystem;
        this.coordX = startX;
        this.coordY = startY;
    }


    public int getCoordX()
    {
        return coordX;
    }

    //setter for coorX
    public void setCoordX(int coorX)
    {
        this.coordX = coorX;

    }

    //getter for coorY
    public int getCoordY()
    {
        return coordY;
    }

    //setter for coordY
    public void setCoordY(int coorY)
    {
        this.coordY = coorY;
    }






}
