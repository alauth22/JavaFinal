package Main;
import Controller.GameEngine;
import View.Window;
import Model.Database;


public enum AppClient {
    //Notes:
    //apply factory design for room blueprint
    //apply abstract logic to item class

    //this is our Singleton instance

        INSTANCE;

        //make instance of both controller and view and database

        private Window window = new Window();
        private Database db = new Database();

        //using the GameEngne constructor
        private GameEngine gameEngine = new GameEngine(window, db);



        //getters and setters
        public GameEngine getEngine() {
            return gameEngine;
        }


        public void setEngine(GameEngine gameengine) {
            this.gameEngine = gameengine;
        }


}
