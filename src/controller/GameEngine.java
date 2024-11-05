package controller;
import View.Window;
import Model.Database;
import gameTimer.GameTimer;
import gameTimer.SurvivalTimer;

import java.util.Timer;


public class GameEngine {

        //private variables to hold the parameter values
        private Window window;
        private Database db;
        private GameTimer gameTimer;
        private SurvivalTimer survivalTimer;

        //GameEngine constructor receiving two parameters.
        public GameEngine(Window window, Database db) {

            this.setDb(db);
            this.setWindow(window);
            setUpGameTimer();
            setUpSurvivalTimer();


        }

        public void setUpGameTimer()
        {
            gameTimer = new GameTimer();

            gameTimer.start();
        }

        public void setUpSurvivalTimer()
        {
            survivalTimer = new SurvivalTimer();
            survivalTimer.setSeconds(10);
            survivalTimer.start();
        }
        public Window getWindow() {
            return window;
        }


        public void setWindow(Window window) {
            this.window = window;
        }


        public Database getDb() {
            return db;
        }


        public void setDb(Database db) {
            this.db = db;
        }

}
