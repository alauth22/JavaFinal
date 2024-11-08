package Model;

public class TimerModel {

    //I do not think we will need this class

    private int survivalTime;
    private int gameTime;

    public TimerModel()
    {

    }

    public int getSurvivalTime()
    {
        return survivalTime;
    }
    public void setSurvivalTime(int time)
    {
        this.gameTime = time;
    }

    public int getGameTime()
    {
        return gameTime;
    }

    public void setGameTime(int time)
    {
        this.gameTime = time;
    }
}
