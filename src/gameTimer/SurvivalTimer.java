package gameTimer;

import java.util.Timer;
import java.util.TimerTask;

public class SurvivalTimer{

    // creates a seconds variable to hold seconds
    public int seconds = 0;
    //creates a timer object
    public Timer timer;

    //instantiates the timer
    public SurvivalTimer()
    {
        timer = new Timer("Survival Timer");
    }
    //starts the timer
    public void start()
    {
        timer.scheduleAtFixedRate(new TimerTask()
        {
            //runs the timer
            @Override
            public void run() {
                if(seconds > 0)
                {
                    seconds--;
                    System.out.println("Time: " + seconds);
                } else {
                    System.out.println();
                    timer.cancel();
                }
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
