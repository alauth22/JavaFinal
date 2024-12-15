package Model.gameTimer;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    // Variable to store the elapsed time in seconds
    private int seconds = 0;

    // Timer object to schedule and manage timing tasks
    private Timer timer;

    // Constructor to initialize the Timer instance with a descriptive name
    public GameTimer() {
        timer = new Timer("Game Timer"); // Create a new Timer with the name "Game Timer"
    }

    // Method to start the timer, incrementing the seconds counter every second
    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++; // Increment the seconds counter
            }
        }, 0, 1000); // Schedule the task to run every 1000 milliseconds (1 second) starting immediately
    }

    // Method to check if the timer has reached a multiple of 15 seconds
    public void checkTimer(Runnable action) {
        if (seconds % 15 == 0 && seconds != 0) { // Check if seconds is a non-zero multiple of 15
            action.run(); // Execute the provided action
        }
    }

    // Method to manually set the elapsed time in seconds
    public void setSeconds(int time) {
        this.seconds = time; // Assign the new time value to the seconds variable
    }

    // Method to retrieve the current elapsed time in seconds
    public int getSeconds() {
        return seconds; // Return the value of the seconds variable
    }
}
