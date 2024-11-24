package Model.levels;

import Model.levels.character.Character;

public class LevelGridSystem {

    private Room[][] levelGrid;
    //private String[][] levelOne;
    private Character player;

    private int XNorth;

    private int XSouth;

    private int YWest;

    private int YEast;

    private int coorX;

    private int coorY;


    public int getXNorth()
    {
        return XNorth;
    }

    public void setXNorth(int xNorth) {
        this.XNorth = xNorth;
    }



    public int getXSouth()
    {
        return XSouth;
    }

    public void setXSouth(int xSouth) {
        this.XSouth = xSouth;
    }




    public int getYWest()
    {
        return YWest;
    }

    public void setYWest(int yWest) {
        this.YWest = yWest;
    }


    public int getYEast()
    {
        return YEast;
    }

    public void setYEast(int yEast) {
        this.YEast = yEast;
    }


    //getter for coorX
    public int getCoorX()
    {
        return coorX;
    }

    //setter for coorX
    public void setCoorX(int coorX)
    {
        this.coorX = coorX;
    }

    //getter for coorY
    public int getCoorY()
    {
        return coorY;
    }

    //setter for coorY
    public void setCoorY(int coorY)
    {
        this.coorY = coorY;
    }




    public LevelGridSystem()
    {
        player = new Character();

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


        if(coorX < 8)
        {
            //level one grid

            //going north, so decrement the X coordinate to go straight up.

            //you are here in the grid
            setRoomToGrid(coorX, coorY, player);
        }

        printMap();

    }


    public void moveSouth()
    {
        //assume we are beginning in the bottom right corner of grid


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
