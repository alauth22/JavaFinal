package controller;
import Model.database.Database;
import Model.levels.Item;
import Model.levels.room.Room;
import Model.levels.room.RoomBuilder;
import Model.levels.room.RoomObjects;
import Model.PlayerCords;
import View.Window;
import Model.gameTimer.GameTimer;
import Model.gameTimer.SurvivalTimer;
import Model.levels.LevelGridSystem;


import java.util.*;

public class ParserEngine {

    //create private sets for nouns and verbs so that they remain unique.
    //could try an array or a dictionary, but I don't care about order.
    private HashSet<String> verbs;
    private HashSet<String> nouns;
    private Window window;
    private Database db;
    private ArrayList<String> commandHistory;
    private LevelGridSystem levels;

    //GAME ENGINE STUFF
    private GameTimer gameTimer;
    private SurvivalTimer survivalTimer;
    private PlayerCords playerCords;

    //level design Rooms
    private Room kitchen;
    private Room livingRoom;
    private Room bathroom;
    private Room halfbath;
    private Room garage;
    //level design room objects;
    private RoomObjects kitchenSink;
    private RoomObjects cabinet;
    private RoomObjects refrigerator;
    private RoomObjects couch;
    private RoomObjects recliner;
    private RoomObjects desk;
    private RoomObjects halfbathSink;
    private RoomObjects bathSink;
    private RoomObjects car;
    private RoomObjects bath;
    private RoomObjects bathroomToilet;
    private RoomObjects halfbathToilet;

    //level design
    //hallways
    private Room hallwayOne;
    private Room hallwayTwo;
    private Room hallwayThree;
    private Room hallwayFour;
    private Room hallwayFive;
    private Room hallwaySix;
    private Room hallwaySeven;

    //constructor
    public ParserEngine(Window window, Database db) {

        this.db = db;
        this.window = window;

        levels = new LevelGridSystem();
        playerCords = new PlayerCords(levels, 7,4);
        //creates the entire house with rooms
        createRooms();



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
        objectSearch(noun, verb);
        showMap(noun, verb);



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

        int currentX = playerCords.getCoordX();
        int currentY = playerCords.getCoordY();

        int newX = currentX;
        int newY = currentY;

        switch (noun.toLowerCase()) {
            case "north":

                newX--;
                System.out.println(levels.getRoomToGrid(newX, newY).enterRoom(null));
                break;
            case "south":

                newX++;
                System.out.println(levels.getRoomToGrid(newX, newY).enterRoom(null));
                break;
            case "west":

                newY--;
                System.out.println(levels.getRoomToGrid(newX, newY).enterRoom(null));
                break;
            case "east":

                newY++;
                System.out.println(levels.getRoomToGrid(newX, newY).enterRoom(null));
                break;
            default:
                //System.out.println("Invalid direction.");
                return;
        }

        if (levels.isValidRoom(newX, newY) && levels.getRoomToGrid(newX, newY) != null) {
            playerCords.setCoordX(newX);
            playerCords.setCoordY(newY);
            //Room currentRoom = levels.getRoomToGrid(newX, newY);
            //System.out.println("You moved " + noun + " to " + currentRoom.getName() + ".");
        } else {
            System.out.println("You can't move " + noun + ". There's no room there.");
        }

    }


    public void createRooms()
    {
        createHallways();
        createBathrooms();
        createGarage();
        createKitchens();
        createLivingRooms();



    }


    public void createHallways()
    {
        hallwayOne = new Room("Hallway");
        hallwayTwo = new Room("Hallway");
        hallwayThree = new Room("Hallway");
        hallwayFour = new Room("Hallway");
        hallwayFive = new Room("Hallway");
        hallwaySix = new Room("Hallway");
        hallwaySeven = new Room("Hallway");


        hallwayOne = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(7,4, hallwayOne);

        hallwayTwo = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(6,4, hallwayTwo);

        hallwayThree = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(5,4, hallwayThree);

        hallwayFour = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(4,4, hallwayFour);

        hallwayFive = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(3,4, hallwayFive);

        hallwaySix = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(3,5, hallwaySix);

        hallwaySeven = new RoomBuilder("Hallway")
                .setLightsOn(true)
                .build();
        levels.setRoomToGrid(3,3, hallwaySeven);


    }


    public void createKitchens()
    {
        kitchen = new Room("Kitchen");
        cabinet = new RoomObjects("Cabinet");
        refrigerator = new RoomObjects("Refrigerator");
        cabinet.addItem(new Item("Key"));


        //this is what needs to be in the search method parser engine
        kitchen = new RoomBuilder("Kitchen")
                .setLightsOn(true)
                .addObject(cabinet)
                .addObject(refrigerator)

                .build();

        levels.setRoomToGrid(6, 3, kitchen);
    }


    public void createLivingRooms()
    {

        couch = new RoomObjects("Couch");
        recliner = new RoomObjects("Recliner");
        desk = new RoomObjects("Desk");

        livingRoom = new RoomBuilder("Living Room").setLightsOn(true)
                .addObject(desk)
                .addObject(recliner)
                .addObject(couch)
                .build();
        levels.setRoomToGrid(4, 3, livingRoom);
    }



    public void createGarage()
    {

    }


    public void createBathrooms()
    {
        bathroom = new Room("Bathroom");
        bathroomToilet = new RoomObjects("toilet");
        bath = new RoomObjects("bath");
        bathSink = new RoomObjects("sink");

        bathroom = new RoomBuilder("Bathroom")
                .setLightsOn(true)
                .addObject(bathroomToilet)
                .addObject(bath)
                .addObject(bathSink)
                .build();
        levels.setRoomToGrid(2, 3, bathroom);

        halfbath = new Room("Bathroom");
        halfbathToilet = new RoomObjects("toilet");
        halfbathSink = new RoomObjects("sink");

        halfbath = new RoomBuilder("Bathroom")
                .setLightsOn(false)
                .addObject(halfbathToilet)
                .addObject(halfbathSink)
                .build();
        levels.setRoomToGrid(7, 5, halfbath);
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
        String[] verbList = {"take", "hide", "lock", "grab", "drop", "open", "exit", "go", "look", "unlock", "turn", "search", "show"};
        verbs.addAll(Arrays.asList(verbList));
        return verbs;
    }


    public HashSet<String> getNouns() {
        //declare a new hashset
        nouns = new HashSet<>();
        String[] nounList = {"key", "door", "room", "flashlight", "award", "upstairs", "downstairs", "drawer", "cabinet",
        "couch", "curtain", "noisemaker", "lights", "window", "fridge", "car", "sink", "desk", "bed", "stove", "shelves",
        "bookshelf", "table", "chair", "nightstand", "counter", "boxes", "timer", "north", "south", "east", "west", "kitchen",
        "bedroom", "hallway", "basement", "livingroom", "bathroom", "refrigerator", "map", "recliner"};
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
        int currentX = playerCords.getCoordX();
        int currentY= playerCords.getCoordY();
        
        //validation for verb search
        if (verb.equals("search")) {

            //continue with switch statement
            switch(noun){
                case "kitchen":
                    if(levels.getRoomToGrid(currentX, currentY) != kitchen)
                    {
                        System.out.println("You are not in a kitchen");
                    }
                    else
                    {
                        System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchRoom());
                    }
                    //which part of the kitchen are you searching first?
                    break;
                case "bedroom":
                    System.out.println();
                    break;
                case "basement":
                    System.out.println();
                    break;
                case "bathroom":
                    if(levels.getRoomToGrid(currentX, currentY) != bathroom && levels.getRoomToGrid(currentX, currentY) != halfbath)
                    {
                        System.out.println("You are not in a bathroom!");
                    }
                    else
                    {
                        System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchRoom());
                    }
                    break;
                case "livingroom":
                    if(levels.getRoomToGrid(currentX, currentY) != livingRoom)
                    {
                        System.out.println("You are not in a living room!");
                    }
                    else
                    {
                        System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchRoom());
                    }
                    break;
                case "hallway":
                    System.out.println();
                    break;

            }


        }


       return result;
    }


    //search cabinet
    public String objectSearch(String noun, String verb)
    {
        String result = "";
        String cabinet = "cabinet";
        int currentX = playerCords.getCoordX();
        int currentY = playerCords.getCoordY();

        //validation for verb search
        if (verb.equals("search")) {
            if(noun.equals(cabinet))
            {
                System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchObject(cabinet));
            }
            else if(noun.equals("refrigerator"))
            {
                System.out.println(levels.getRoomToGrid(currentX,currentY).searchObject("refrigerator"));
            }
            else if(noun.equals("desk"))
            {
                System.out.println(levels.getRoomToGrid(currentX,currentY).searchObject("desk"));
            }
        }



        return result;

    }

    public void showMap(String noun, String verb)
    {
        if(verb.equals("show"))
        {
            if(noun.equals("map"))
            {
                levels.printMap(playerCords);
            }
        }
    }


}