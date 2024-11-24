package Model;

public class PlayerCords {

    private int coorX;
    private int coorY;


    public PlayerCords()
    {
        this.coorX = 7;
        this.coorY = 7;
    }


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


}
