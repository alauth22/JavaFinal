package Model.gameTimer;

import java.util.Timer;
import java.util.TimerTask;

public class SurvivalTimer {

    // Variable to store the remaining time in seconds
    public int seconds = 0;

    // Timer object to schedule and manage timing tasks
    public Timer timer;

    // Constructor to initialize the Timer instance with a descriptive name
    public SurvivalTimer() {
        timer = new Timer("Survival Timer"); // Create a new Timer with the name "Survival Timer"
    }

    // Method to start the countdown timer
    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            // Runs the countdown logic every second
            @Override
            public void run() {
                if (seconds > 0) { // Check if there is remaining time
                    seconds--;
                    System.out.println("Time: " + seconds); // Print the current time
                } else {
                    System.out.println(); // Print an empty line to signify the timer's end
                    timer.cancel(); // Stop the timer when time runs out
                }
            }
        }, 0, 1000); // Schedule the task to run every 1000 milliseconds (1 second) starting immediately
    }

    // Method to manually set the starting time in seconds
    public void setSeconds(int time) {
        this.seconds = time;
    }

    // Method to retrieve the remaining time in seconds
    public int getSeconds() {
        return seconds;
    }
}
