package Model;

import Model.levels.LevelGridSystem;


public class PlayerCords {

    // X-coordinate of the player's position
    private int coordX;

    // Y-coordinate of the player's position
    private int coordY;

    // Reference to the level grid system the player is on
    private LevelGridSystem levelGridSystem;

    // Constructor to initialize the player's position and associate it with a level grid system
    public PlayerCords(LevelGridSystem levelGridSystem, int startX, int startY) {
        this.levelGridSystem = levelGridSystem; // Set the grid system reference
        this.coordX = startX;                  // Set the starting X-coordinate
        this.coordY = startY;                  // Set the starting Y-coordinate
    }

    // Getter method to retrieve the player's X-coordinate
    public int getCoordX() {
        return coordX;
    }

    // Setter method to update the player's X-coordinate
    public void setCoordX(int coorX) {
        this.coordX = coorX; // Assign the new X-coordinate
    }

    // Getter method to retrieve the player's Y-coordinate
    public int getCoordY() {
        return coordY;
    }

    // Setter method to update the player's Y-coordinate
    public void setCoordY(int coorY) {
        this.coordY = coorY; // Assign the new Y-coordinate
    }
}

