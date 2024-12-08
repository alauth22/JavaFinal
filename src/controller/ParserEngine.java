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

    private Room kitchen;
    //private Cabinet cabinet1;
    private RoomObjects cabinet;
    private RoomObjects refrigerator;

    //level design
    Room hallwayOne;
    Room hallwayTwo;
    Room hallwayThree;
    Room hallwayFour;
    Room hallwayFive;
    Room hallwaySix;
    Room hallwaySeven;

    //constructor
    public ParserEngine(Window window, Database db) {

        this.db = db;
        this.window = window;

        levels = new LevelGridSystem();
        playerCords = new PlayerCords(levels, 4,4);
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





    //method to scan and send appropriate messages to the player.
    public void scanText() {

        StringBuilder sbVerb = new StringBuilder();
        sbVerb = showVerbs();

        StringBuilder sbNoun = new StringBuilder();
        sbNoun = showNouns();



        //create a new scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO HOME INTRUSION " + "\n" +
                "Enter a verb-and-noun command to begin game. " + "\n\n" +
                "Select any verbs from this list: " + "\n" + sbVerb.toString() + "\n\n" +
                "Select any nouns from this list: " + "\n" + sbNoun.toString() + "\n\n" +
                "You will have 3 minutes to run through the home and grab essential items before the timer runs out and the " +
                "intruder has caught you. Good luck! ");

        //begin the while loop to loop through every person's turn until the game ends.
        while (true)
        {
            //send message prompting user to type a command.
            System.out.println("Enter Command: ");
            //String userInput = scanner.nextLine();
            int attempts = 0;
            boolean isCorrect = false;

            while (attempts < 3) {
                // grab the user's input and set equal to a variable.
                String userInput = scanner.nextLine();

                // Add logic here to validate user input, for example:
                if (isValidInput(userInput)) {
                    // Continue with the processing
                    String[] parsedInput = parseInput(userInput);
                    System.out.println("Command Executed!");
                    isCorrect = true;  // Stop after a valid command
                    break;
                } else {
                    attempts++;
                    System.out.println("Invalid command. Try again!");
                }
            }


            if (attempts == 3)
            {
                System.out.println("You have exhausted 3 attempts! Please enter a valid command next time.");
                System.out.println("Enter Exit to quit the game.");
            }

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



    /*
    Method checks if the String user input is actually a string or not.
     */
    public Boolean isValidInput(String input)
    {
        // Convert input to lowercase and split by space
        String[] words = input.toLowerCase().split(" ");

        // Variables to store verb and noun
        String verb = null;
        String noun = null;

        // Loop through words and check if they're in the sets
        for (String w : words) {
            if (verbs.contains(w)) {
                verb = w;
            } else if (nouns.contains(w)) {
                noun = w;
            }
        }

        // Check if both verb and noun were found
        if (verb != null && noun != null) {
            return true;  // Both verb and noun are valid
        } else {
            return false;  // Invalid input
        }

    }





    //method that parses user input and returns the string array.
    public String[] parseInput(String input) {

        // First, validate input
        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please ensure you use a valid verb and noun.");
            return new String[]{null, null};  // Return a default array for invalid input
        }

        // Now that we know input is valid, proceed with parsing
        String[] words = input.toLowerCase().split(" ");
        String verb = null;
        String noun = null;

        // Loop through words and assign verb/noun
        for (String w : words) {
            if (verbs.contains(w)) {
                verb = w;
            } else if (nouns.contains(w)) {
                noun = w;
            }
        }

        // Add verb and noun to command history
        commandHistory.add(verb);
        commandHistory.add(noun);

        /*
        For some reason, 
         */

        // Track movement and perform searches (testing purpose)
        if(verb == "search")
        {
            RoomSearch(noun, verb);
            CabinetSearch(noun, verb);

        }
        else
        {
            trackMovement(verb, noun);
            showMap(noun, verb);

        }

        // Return verb and noun as a String array
        return new String[]{verb, noun};

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
                break;
            case "south":
                newX++;
                break;
            case "west":
                newY--;
                break;
            case "east":
                newY++;
                break;
            default:
                System.out.println("Invalid direction.");
                return;
        }

        if (levels.isValidRoom(newX, newY) && levels.getRoomToGrid(newX, newY) != null) {
            playerCords.setCoordX(newX);
            playerCords.setCoordY(newY);
            Room currentRoom = levels.getRoomToGrid(newX, newY);
            System.out.println("You moved " + noun + " to " + currentRoom.getName() + ".");
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

    }


    public void createGarage()
    {

    }


    public void createBathrooms()
    {

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
        "bedroom", "hallway", "basement", "living room", "bathroom", "refrigerator", "map"};
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
                    System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()));
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

//        else
//        {
//            System.out.println("Please use the verb Search in your command to search the room.");
//        }

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
            else if(noun.equals("refrigerator"))
            {
                System.out.println(refrigerator.search());
            }
        }

//        else
//        {
//            System.out.println("Please use the verb Search in your command to search the room.");
//        }

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

    public StringBuilder showVerbs()
    {
        StringBuilder sb = new StringBuilder();
        HashSet<String> VERB = getVerbs();
        ArrayList<String> VERBArray = new ArrayList<>(VERB);

        for(int i = 0; i < VERBArray.size(); i++)
        {
            if (i == VERBArray.size() - 1)
            {
                sb.append(VERBArray.get(i));
            }
            else
            {
                sb.append(VERBArray.get(i)).append(", ");
            }
        }

        return sb;

    }


    public StringBuilder showNouns()
    {
        StringBuilder sb = new StringBuilder();
        HashSet<String> NOUN = getNouns();
        ArrayList<String> NOUNArray = new ArrayList<>(NOUN);

        for(int i = 0; i < NOUNArray.size(); i++)
        {
            if (i == NOUNArray.size() - 1)
            {
                sb.append(NOUNArray.get(i));
            }
            else
            {
                sb.append(NOUNArray.get(i)).append(", ");
            }
        }

        return sb;
    }


}