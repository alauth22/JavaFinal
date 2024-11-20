package Main;
import Model.EntityModel;
import View.GameText;
import controller.ParserEngine;
import Model.Database;


public enum AppClient {
    //Notes:
    //apply factory design for room blueprint
    //apply abstract logic to item class

    //this is our Singleton instance

        INSTANCE;

        //make instance of both controller and view and database

        private GameText gameText = new GameText();
        private EntityModel entityModel = new EntityModel();
        private Database db = new Database();

        //using the GameEngne constructor
        //private GameEngine gameEngine = new GameEngine(window, db);
        private ParserEngine parserEngine  = new ParserEngine(gameText, db, entityModel);


        //getters and setters
//        public GameEngine getEngine() {return gameEngine;}

        public ParserEngine getParserEngine() {return parserEngine; }





}
