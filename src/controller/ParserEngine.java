package controller;
import Model.Database;
import Model.Levels2.Cabinet;
import Model.Levels2.Item;
import Model.Levels2.Room;
import Model.Levels2.RoomBuilder;
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

    //create private sets for nouns and verbs so that they remain unique.
    //could try an array or a dictionary, but I don't care about order.
    private HashSet<String> verbs;
    private HashSet<String> nouns;
    private Window window;
    private Database db;
    private ArrayList<String> commandHistory;
    private LevelGridSystem levelGridSystem;

    //GAME ENGINE STUFF
    private GameTimer gameTimer;
    private SurvivalTimer survivalTimer;
    private LevelGridSystem levels;
    private PlayerCords playerCords;

    private Room Kitchen;
    //private Cabinet cabinet1;
    private Item kitchenkey;
    private Cabinet cabinet;

    //constructor
    public ParserEngine(Window window, Database db, PlayerCords playerCords) {

        this.db = db;
        this.window = window;
        this.playerCords = playerCords;
        levels = new LevelGridSystem();

        //creates the entire house with rooms
        createRooms();

        kitchenkey = new Item("Kitchen Key");
        cabinet = new Cabinet().addItem(kitchenkey);

        //this is what needs to be in the search method parser engine
        Kitchen = new RoomBuilder("Kitchen")
                .setLocked(kitchenkey)
                .setLightsOn(true)
                .addObject(cabinet)
                .build();

        //initialize the HashSet, which implements the Set interface
        verbs = new HashSet<>();
        nouns = new HashSet<>();
        commandHistory = new ArrayList<>();

        /*
        Calls respective methods that will populate the hashsets with specific verb and noun string values.
         */
        verbs = getVerbs();
        nouns = getNouns();


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

        //OUR TESTING BY CALLING THESE METHODS
        trackMovement(verb, noun);
        RoomSearch(noun, verb);
        CabinetSearch(noun, verb);



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


    public HashSet<String> getVerbs() {
        //declare a new hashset
        verbs = new HashSet<>();
        String[] verbList = {"take", "hide", "lock", "grab", "drop", "open", "exit", "go", "look", "unlock", "turn", "search"};
        verbs.addAll(Arrays.asList(verbList));
        return verbs;
    }


    public HashSet<String> getNouns() {
        //declare a new hashset
        nouns = new HashSet<>();
        String[] nounList = {"key", "door", "room", "flashlight", "award", "upstairs", "downstairs", "drawer", "cabinet",
        "couch", "curtain", "noisemaker", "lights", "window", "fridge", "car", "sink", "desk", "bed", "stove", "shelves",
        "bookshelf", "table", "chair", "nightstand", "counter", "boxes", "timer", "north", "south", "east", "west", "kitchen",
        "bedroom", "hallway", "basement", "living room", "bathroom"};
        nouns.addAll(Arrays.asList(nounList));
        return nouns;
    }


    /*
    Search Method:
    "search" - "noun - room name"
    go to that builder class caller the roomBuilder
    grab the items in the builder class

    add the room names in the noun HashSet<>
     */

    public String RoomSearch(String noun, String verb) {

        String result = "";
        
        
        //validation for verb search
        if (verb.equals("search")) {

            //continue with switch statement
            switch(noun){
                case "kitchen":
                    System.out.println(Kitchen.searchRoom());
                    //which part of the kitchen are you searching first?
                    break;
                case "bedroom":
                    System.out.println();
                    break;
                case "basement":
                    System.out.println();
                    break;
                case "bathroom":
                    System.out.println();
                    break;
                case "living room":
                    System.out.println();
                    break;
                case "hallway":
                    System.out.println();
                    break;

            }


        }

        else
        {
            System.out.println("Please use the verb Search in your command to search the room.");
        }

       return result;
    }


    //search cabinet
    public String CabinetSearch(String noun, String verb)
    {
        String result = "";

        //validation for verb search
        if (verb.equals("search")) {
            if(noun.equals("cabinet"))
            {
                System.out.println(cabinet.search());
            }
        }

        else
        {
            System.out.println("Please use the verb Search in your command to search the room.");
        }

        return result;

    }



}