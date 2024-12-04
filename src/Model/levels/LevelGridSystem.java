package Model.levels;


import Model.PlayerCords;
import Model.levels.room.Room;

public class LevelGridSystem {

    private Room[][] levelGrid;
    //private String[][] levelOne;


    private int rows;

    private int cols;





    public LevelGridSystem()
    {
        levelGrid = new Room[8][8];
    }

    public void setRoomToGrid(int rows, int columns, Room room)
    {
        if(rows >= 0 && rows < levelGrid.length && columns < levelGrid[0].length)
        {
            levelGrid[rows][columns] = room;
        } else {
            System.out.println("invalid coords");
        }
    }
    public Room getRoomToGrid(int rows, int cols)
    {
        if (rows >= 0 && rows < levelGrid.length && cols >= 0 && cols < levelGrid[0].length) {
            return levelGrid[rows][cols];
        }
        return null;
    }


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

    public boolean isValidRoom(int row, int col)
    {
        return row >= 0 && row < levelGrid.length && col >= 0 && col < levelGrid[0].length;
    }



//
//
//    public void moveNorth()
//    {
//        //assume we are beginning in the bottom right corner of grid
//
//
//        if(coorX < 8)
//        {
//            //level one grid
//
//            //going north, so decrement the X coordinate to go straight up.
//
//            //you are here in the grid
//            setRoomToGrid(coorX, coorY, player);
//        }
//
//        printMap();
//
//    }
//


//    public void moveSouth()
//    {
//        //assume we are beginning in the bottom right corner of grid
//
//
//        if(coorX < 8)
//        {
//            //level one grid
//
//            //going north, so decrement the X coordinate to go straight up.
//            coorX++;
//            //you are here in the grid
//            setRoomToGrid(coorX, coorY, player);
//        }
//
//        printMap();
//
//    }
//
//    public void moveEast()
//    {
//        //assume we are beginning in the bottom right corner of grid
//
//
//
//        if(coorY < 8)
//        {
//            //level one grid
//
//            //going north, so decrement the X coordinate to go straight up.
//            coorY++;
//            //you are here in the grid
//            setRoomToGrid(coorX, coorY, player);
//        }
//
//        printMap();
//
//    }
//
//    public void moveWest()
//    {
//
//        //assume we are beginning in the bottom right corner of grid
//
//
//
//        if(coorY < 8)
//        {
//            //level one grid
//
//            //going north, so decrement the X coordinate to go straight up.
//            coorY--;
//            //you are here in the grid
//            setRoomToGrid(coorX, coorY, player);
//        }
//
//        printMap();
//
//    }

}
