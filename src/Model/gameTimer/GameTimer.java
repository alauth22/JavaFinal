package Model.gameTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private boolean isGameOver = false;
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
        Scanner scanner = new Scanner(System.in);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //if the game is NOT over or false
                if (!isGameOver) {
                    seconds++;
                    //check if the timer has reached 60 seconds
                    if (seconds >= 120) {
                        //end the game.
                        endGame();
                        scanner.close();
                    }
                }
            }

        }, 0, 1000);
    }

    // Method to check if the timer has reached a multiple of 15 seconds
    public void checkTimer(Runnable action) {
        if (seconds % 15 == 0 && seconds != 0) { // Check if seconds is a non-zero multiple of 15
            action.run(); // Execute the provided action
        }
    }
    /*
    Method to start the timer, increment the seconds counter for every second.
     */


    // Method to manually set the elapsed time in seconds
    public void setSeconds(int time) {
        this.seconds = time; // Assign the new time value to the seconds variable
    }

    // Method to retrieve the current elapsed time in seconds
    public int getSeconds() {
        return seconds; // Return the value of the seconds variable
    }

    public void endGame()
    {
        isGameOver = true;
        timer.cancel();
        System.out.println("Game is Over! You ran out of time and lost the game!");
    }

    //End game if the player won
    public void endGameWon(){
        timer.cancel();
        System.out.println("Game finished. You won!");
    }
}
