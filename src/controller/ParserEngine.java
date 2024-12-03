package controller;
import Model.PlayerCords;
import View.Window;
import Model.gameTimer.GameTimer;
import Model.gameTimer.SurvivalTimer;
import Model.levels.LevelGridSystem;
import Model.levels.bathroom.Bath;
import Model.levels.bathroom.BathBuilder;
import Model.levels.garage.Garage;
import Model.levels.garage.GarageBuilder;
import Model.levels.hallway.Hallway;
import Model.levels.hallway.HallwayBuilder;
import Model.levels.kitchen.Kitchen;
import Model.levels.kitchen.KitchenBuilder;
import Model.levels.livingroom.Living;
import Model.levels.livingroom.LivingBuilder;

import java.util.*;

public class ParserEngine {
    //Scanner Parser

    //create private sets for nouns and verbs so that they remain unique.
    //could try an array or a dictionary, but I don't care about order.
    private Set<String> verbs;
    private Set<String> nouns;
    private Window window;
    private Database db;
    private ArrayList<String> commandHistory;
    private LevelGridSystem levelGridSystem;

    //GAME ENGINE STUFF
    //private variables to hold the parameter values
    //private Window window;
    //private Database db;
    private GameTimer gameTimer;
    private SurvivalTimer survivalTimer;
    private LevelGridSystem levels;
    private PlayerCords playerCords;

    //constructor
    public ParserEngine(Window window, Database db, PlayerCords playerCords) {

        this.db = db;
        this.window = window;
        this.playerCords = playerCords;


        //GAME ENGINE
        //this.setDb(db);
        //this.setWindow(window);
        levels = new LevelGridSystem();
        //setUpGameTimer();
        //setUpSurvivalTimer();
        createRooms();

        //initialize the HashSet, which implements the Set interface
        verbs = new HashSet<>();
        nouns = new HashSet<>();
        commandHistory = new ArrayList<>();

        //add to verbs set.
        verbs.add("take"); //keep
        verbs.add("hide"); //keep
        verbs.add("lock"); //keep
        verbs.add("grab"); //keep
        verbs.add("drop"); //keep
        verbs.add("open"); //keep
        verbs.add("exit"); //keep
        verbs.add("go"); //keep
        verbs.add("look"); //keep
        verbs.add("unlock"); //keep
        verbs.add("turn");
//        verbs.add("turn on"); //keep
//        verbs.add("turn off");

        //add to nouns set.
        nouns.add("key");
        nouns.add("door");
        nouns.add("room");
        nouns.add("flashlight");
        nouns.add("award");
        nouns.add("upstairs");
        nouns.add("downstairs");
        nouns.add("drawer");
        nouns.add("cabinet");
        nouns.add("couch");
        nouns.add("curtain");
        nouns.add("noisemaker");
        nouns.add("lights");
        nouns.add("window");
        nouns.add("fridge");
        nouns.add("car");
        nouns.add("sink");
        nouns.add("desk");
        nouns.add("bed");
        nouns.add("stove");
        nouns.add("shelves");
        nouns.add("bookshelf");
        nouns.add("table");
        nouns.add("chair");
        nouns.add("nightstand");
        nouns.add("counter");
        nouns.add("boxes");
        nouns.add("timer");
        nouns.add("north");
        nouns.add("south");
        nouns.add("west");
        nouns.add("east");

    }



    //
    //method that parses user input and returns the string array.
    public String[] parseInput(String input) {

        /*
        enter a room, look around
        builder see a cabinet and a fridge,
        search individually and smaller objects
        and search those
        */

        //set these variables equal to null.
        String verb = null;
        String noun = null;

        //set everything to lowercase and split it by space.
        String[] words = input.toLowerCase().split(" ");

        //now check if the word is in our sets through a foreach loop.
        for (String w : words) {
            if(verbs.contains(w))
            {
                verb = w;
            }
            else if (nouns.contains(w))
            {
                noun = w;
            }
            else
            {
                System.out.println("This verb and/or noun do not exist in our database. Please type a new command!");
            }

        }

        if (verb == null)
        {
            System.out.println("Invalid verb. Please try again!");
        }
        if (noun == null)
        {
            System.out.println("Invalid noun. Please try again!");
        }

        //add the verb and noun to commandHistory to keep track of user's moves.
        if(verb != null && noun != null)
        {
            commandHistory.add(verb);
            commandHistory.add(noun);
        }

        trackMovement(verb, noun);

        /*
        I need to ensure that the string array returned is indeed in the order of [0] = verb
        and [1] = noun for future usage. For this reason, I'm choosing an Array and not an Arraylist
        because I don't want the size of that Array to dynamically change. Will return a new
        array of size 2 with the new verb and noun.
        */
        return new String[]{verb, noun};

    }




    //method to scan and send appropriate messages to the player.
    public void scanText() {

        //create a new scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To Home Intrusion! Start the game and enter a verb and noun command " +
                "(ex: 'Take Key'): ");

        //begin the while loop to loop through every person's turn until the game ends.
        while (true)
        {
            //send message prompting user to type a command.
            System.out.println("Enter Command: ");

            //grab the user's input and set equal to a variable.
            String userInput = scanner.nextLine();


            //the loop will only break if the user uses the word "Exit"
            if (userInput.equalsIgnoreCase("exit"))
            {
                //break out of the loop.
                break;
            }

            //call my function to parse the userinput.
            parseInput(userInput);
        }

        System.out.println("\n Game Over!");
        showCommand();
        commandHistory.clear();
        //Close the scanner
        scanner.close();

    }



    private void showCommand()
    {
        for(int i = 0; i < commandHistory.size(); i += 2)
        {
            String verbValue = commandHistory.get(i);
            String nounValue = commandHistory.get(i + 1);
            System.out.println("Verb: " + verbValue  + " |" + " Noun: " + nounValue);
        }
    }



    //switch case method to keep track of
    private void trackMovement(String verb, String noun)
    {

        /*
        if verb = go
        then we need a switch statement for noun for north, south, east, OR west
         */

        if(verb.equalsIgnoreCase("go"))
        {
            int xNorth = playerCords.getCoorX() - 1;
            int xSouth = playerCords.getCoorX() + 1;

            int yEast = playerCords.getCoorY() + 1;
            int yWest = playerCords.getCoorY() - 1;

            levels.setCoorX(xNorth);
            //levels.getCoorX(xSouth);
            levels.setCoorY(yWest);



            switch (noun) {
                case "north":
                    System.out.println("Going North!");
                    playerCords.setCoorX(xNorth);
                    levels.moveNorth();
                    break;
                case "south":
                    System.out.println("Going South!");
                    playerCords.setCoorX(xSouth);
                    levels.moveSouth();
                    break;
                case "west":
                    System.out.println("Going West!");
                    playerCords.setCoorY(yWest);
                    levels.moveWest();
                    break;
                case "east":
                    System.out.println("Going East!");
                    playerCords.setCoorY(yEast);
                    levels.moveEast();
                    break;

            }


        }

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

    //create methods to trigger an update between two tables (Inventory and Item)
    //player push button --> trigger methods --> take knife out of Inventory table and put it into Item table
    //if there are 3 knives --> 2 knives left in Inventory table and 1 knife in Item table 



}