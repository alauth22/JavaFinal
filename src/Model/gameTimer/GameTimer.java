package Model.gameTimer;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {


    private int seconds = 0;
    private Timer timer;

    public GameTimer()
    {
        timer = new Timer("Game Timer");

    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;

            }
        }, 0, 1000);
    }
    public void checkTimer(Runnable action) {
        if(seconds % 15 == 0 && seconds != 0)
        {
           action.run();

        }
    }
    public void setSeconds(int time)
    {
        this.seconds = time;
    }
    public int getSeconds()
    {
        return seconds;
    }
}
