package controller;
import View.Window;
import Model.Database;
import gameTimer.GameTimer;
import gameTimer.SurvivalTimer;
import levels.LevelGridSystem;
import levels.bathroom.Bath;
import levels.bathroom.BathBuilder;
import levels.garage.Garage;
import levels.garage.GarageBuilder;
import levels.hallway.Hallway;
import levels.hallway.HallwayBuilder;
import levels.kitchen.Kitchen;
import levels.kitchen.KitchenBuilder;
import levels.livingroom.Living;
import levels.livingroom.LivingBuilder;

import java.util.Timer;


public class GameEngine {

        //private variables to hold the parameter values
        private Window window;
        private Database db;
        private GameTimer gameTimer;
        private SurvivalTimer survivalTimer;
        private LevelGridSystem levels;
        //GameEngine constructor receiving two parameters.
        public GameEngine(Window window, Database db) {

            this.setDb(db);
            this.setWindow(window);
            levels = new LevelGridSystem();
            setUpGameTimer();
            setUpSurvivalTimer();
            createRooms();

        }
    public void createRooms()
    {
        createHallways();
        createBathrooms();
        createGarage();
        createKitchens();
        createLivingRooms();


        levels.printMap();
    }

    public void createHallways()
    {
        Hallway hallwayOne = new HallwayBuilder().picture().build();
        levels.setRoomToGrid(7, 4, hallwayOne);

        Hallway hallwayTwo = new HallwayBuilder().build();
        levels.setRoomToGrid(6, 4, hallwayTwo);

        Hallway hallwayThree = new HallwayBuilder().build();
        levels.setRoomToGrid(5,4,hallwayThree);
    }

    public void createKitchens()
    {
        Kitchen kitchenOne = new KitchenBuilder().bath().toilet().cabinet().build();
        levels.setRoomToGrid(6, 3, kitchenOne);
    }

    public void createLivingRooms()
    {
        Living livingOne = new LivingBuilder().recliner().couch().build();
        levels.setRoomToGrid(5,3, livingOne);
    }

    public void createGarage()
    {
        Garage garageOne = new GarageBuilder().car().toolBox().desk().build();
        levels.setRoomToGrid(6, 5, garageOne);
    }
    public void createBathrooms()
    {
        Bath bathOne = new BathBuilder().toilet().shower().sink().build();
        levels.setRoomToGrid(6, 6, bathOne);
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
