package gameTimer;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private int seconds = 0;
    private Timer timer;

    public GameTimer()
    {
        timer = new Timer("Game Timer");

    }

    public void start()
    {
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
              seconds++;
              System.out.println("Game Timer: " + seconds);
            }


        }, 0, 1000);
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
