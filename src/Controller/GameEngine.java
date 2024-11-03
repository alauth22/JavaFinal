package Controller;
import View.Window;
import Model.Database;


public class GameEngine {

        //private variables to hold the parameter values
        private Window window;
        private Database db;


        //GameEngine constructor receiving two parameters.
        public GameEngine(Window window, Database db) {

            this.setDb(db);
            this.setWindow(window);

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
