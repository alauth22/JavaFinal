package levels;

import levels.character.Character;

public class LevelGridSystem {

    private Room[][] levelGrid;
    //private String[][] levelOne;
    Character player;

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
    public Room getRoomToGrid(int rows, int columns)
    {
        if (rows >= 0 && rows < levelGrid.length && columns >= 0 && columns < levelGrid[0].length) {
            return levelGrid[rows][columns];
        }
        return null;
    }


    public void printMap() {
        for (int row = 0; row < levelGrid.length; row++) {
            for (int col = 0; col < levelGrid[row].length; col++) {
                if (levelGrid[row][col] == null) {
                    System.out.print("* ");
                } else {
                    System.out.print(levelGrid[row][col].getRoomSymbol() + " ");
                }
            }
            System.out.println();
        }
    }


    public void moveNorth()
    {
        //assume we are beginning in the bottom right corner of grid
        int coorX = 7;
        int coorY = 7;
        player = new Character();

        if(coorX < 8)
        {
            //level one grid

            //going north, so decrement the X coordinate to go straight up.
            coorX--;
            //you are here in the grid
            setRoomToGrid(coorX, coorY, player);
        }

        printMap();

    }


    public void moveSouth()
    {
        //assume we are beginning in the bottom right corner of grid
        int coorX = 7;
        int coorY = 7;
        player = new Character();

        if(coorX < 8)
        {
            //level one grid

            //going north, so decrement the X coordinate to go straight up.
            coorX++;
            //you are here in the grid
            setRoomToGrid(coorX, coorY, player);
        }

        printMap();

    }

    public void moveEast()
    {
        //assume we are beginning in the bottom right corner of grid
        int coorX = 7;
        int coorY = 7;
        player = new Character();

        if(coorY < 8)
        {
            //level one grid

            //going north, so decrement the X coordinate to go straight up.
            coorY++;
            //you are here in the grid
            setRoomToGrid(coorX, coorY, player);
        }

        printMap();

    }

    public void moveWest()
    {

        //assume we are beginning in the bottom right corner of grid
        int coorX = 7;
        int coorY = 7;
        player = new Character();

        if(coorY < 8)
        {
            //level one grid

            //going north, so decrement the X coordinate to go straight up.
            coorY--;
            //you are here in the grid
            setRoomToGrid(coorX, coorY, player);
        }

        printMap();

    }

}
