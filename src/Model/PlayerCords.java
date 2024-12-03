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

    public void move(String direction)
    {
        int newX = coordX;
        int newY = coordY;

        switch (direction.toLowerCase()) {
            case "north":
                newX -= 1;
                break;
            case "south":
                newX += 1;
                break;
            case "west":
                newY -= 1;
                break;
            case "east":
                newY += 1;
                break;
            default:
                System.out.println("Invalid direction.");
                return;
        }

        if (levelGridSystem.isValidRoom(newX, newY)) {
            coordX = newX;
            coordY = newY;
            System.out.println("Moved " + direction + " to (" + coordX + ", " + coordY + ").");
        } else {
            System.out.println("You can't move " + direction + ". No room there!");
        }
    }



}
