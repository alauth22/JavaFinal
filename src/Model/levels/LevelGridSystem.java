package Model.levels;

import Model.PlayerCords;
import Model.levels.room.Room;

public class LevelGridSystem {

    //declare variables
    private Room[][] levelGrid;
    private int rows;
    private int cols;

    //constructor
    public LevelGridSystem()
    {
        levelGrid = new Room[8][8];
    }

    //method to set the room to the 2D grid map.
    public void setRoomToGrid(int rows, int columns, Room room)
    {
        if(rows >= 0 && rows < levelGrid.length && columns < levelGrid[0].length)
        {
            levelGrid[rows][columns] = room;
        } else {
            System.out.println("invalid coords");
        }
    }

    //Method to return the room letter id from the grid.
    public Room getRoomToGrid(int rows, int cols)
    {
        if (rows >= 0 && rows < levelGrid.length && cols >= 0 && cols < levelGrid[0].length) {
            return levelGrid[rows][cols];
        }
        return null;
    }


    //print and display the map.
    public void printMap(PlayerCords playerCords) {
        for (int row = 0; row < levelGrid.length; row++) {
            for (int col = 0; col < levelGrid[row].length; col++) {
                if (row == playerCords.getCoordX() && col == playerCords.getCoordY()) {
                    System.out.print("P "); // Indicate player position
                } else if (levelGrid[row][col] == null) {
                    System.out.print("* "); // Empty tile
                } else {
                    System.out.print(levelGrid[row][col].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    //check if that item in the room is an actual room. Return true or false.
    public boolean isValidRoom(int row, int col)
    {
        return row >= 0 && row < levelGrid.length && col >= 0 && col < levelGrid[0].length;
    }

}
