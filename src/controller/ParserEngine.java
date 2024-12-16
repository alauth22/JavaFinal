package controller;

import Model.database.Database;
import Model.database.Player;
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
    private Player playerDB;
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


    private Room bathroom;
    private RoomObjects sink;
    private RoomObjects tub;
    private RoomObjects toilet;
    private RoomObjects cabinet2;

    private Room bedroom;
    private RoomObjects bed;
    private RoomObjects dresser;
    private RoomObjects vanity;

    private Room livingRoom;
    private RoomObjects sofa;
    private RoomObjects lamp;
    private RoomObjects piano;
    private RoomObjects tv;
    private RoomObjects table;

    private Room garage;
    private RoomObjects car;
    private RoomObjects mower;


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


        //get a new grid system that will be our map.
        levels = new LevelGridSystem();
        //start the player off at these coordinates in the 2D grid
        playerCords = new PlayerCords(levels, 4,4);
        //creates the entire house with rooms that we have designated already
        createRooms();



        //initialize the HashSet, which implements the Set interface to hold all the nouns and verbs
        //allowed in the game.
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

        StringBuilder sbVerb;
        sbVerb = showVerbs();

        StringBuilder sbNoun;
        sbNoun = showNouns();

        //create a new scanner for user input with directions
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO HOME INTRUSION " + "\n" +
                "Enter a verb-and-noun command to begin game. " + "\n\n" +
                "Select any verbs from this list: " + "\n" + sbVerb.toString() + "\n\n" +
                "Select any nouns from this list: " + "\n" + sbNoun.toString() + "\n\n" +
                "You will have 30 seconds to run through the home and grab essential items before the timer runs out and the " +
                "intruder has caught you. If a word is misspelled, you have 3 chances to correct and continue the game. Good luck! ");

        //begin the while loop to loop through every person's turn until the game ends.
        while (true)
        {
            //send message prompting user to type a command.
            System.out.println("Enter Command: ");
            //String userInput = scanner.nextLine();
            int attempts = 0;
            boolean isCorrect = false;

            while (attempts < 3) {
                //grab the user's input and set equal to a variable.
                String userInput = scanner.nextLine();

                //Add logic here to validate user input, for example:
                if (isValidInput(userInput)) {
                    //Continue with the processing
                    String[] parsedInput = parseInput(userInput);
                    System.out.println("Command Executed!");
                    isCorrect = true;  // Stop after a valid command
                    break;
                }
                else
                {
                    attempts++;
                    System.out.println("Invalid command. Try again!");
                }
            }

            //if the attempts reach to 3 tries, force user to end the game.
            if (attempts == 3)
            {
                System.out.println("You have exhausted 3 attempts! Please enter a valid command next time.");
                System.out.println("Enter Exit to quit the game.");
            }

            //get the next input from the user.
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

        //tell user the game is over.
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
        //Convert input to lowercase and split by space
        String[] words = input.toLowerCase().split(" ");

        //Variables to store verb and noun
        String verb = null;
        String noun = null;

        //Loop through words and check if they're in the sets
        for (String w : words) {
            if (verbs.contains(w)) {
                verb = w;
            } else if (nouns.contains(w)) {
                noun = w;
            }
        }

        //Check if both verb and noun were found
        if (verb != null && noun != null) {
            return true;  // Both verb and noun are valid
        } else {
            return false;  // Invalid input
        }

    }


    //Method that parses user input and returns the string array.
    public String[] parseInput(String input) {

        //First, validate input
        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please ensure you use a valid verb and noun.");
            return new String[]{null, null};  // Return a default array for invalid input
        }

        //Now that we know input is valid, proceed with parsing
        String[] words = input.toLowerCase().split(" ");
        String verb = null;
        String noun = null;

        //Loop through words and assign verb/noun
        for (String w : words) {
            if (verbs.contains(w)) {
                verb = w;
            } else if (nouns.contains(w)) {
                noun = w;
            }
        }

        //Add verb and noun to command history
        commandHistory.add(verb);
        commandHistory.add(noun);

        RoomSearch(noun, verb);
        CabinetSearch(noun, verb);
        GrabItemCabinet(noun, verb);
        trackMovement(verb, noun);
        showMap(noun, verb);

        //Return verb and noun as a String array
        return new String[]{verb, noun};

    }


    /*
    Method to just show the command history once the person exists the game.
     */
    private void showCommand()
    {
        for(int i = 0; i < commandHistory.size(); i += 2)
        {
            String verbValue = commandHistory.get(i);
            String nounValue = commandHistory.get(i + 1);
            System.out.println("Verb: " + verbValue  + " |" + " Noun: " + nounValue);
        }
    }


    //switch case method to keep track of the player's movement throughout the map.
    private void trackMovement(String verb, String noun)
    {
        /*
        if verb equals "go" then we need a switch statement for noun for north, south, east, OR west
         */

        //get the current coordinates
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
        }

        //condition to move around the map
        if (levels.isValidRoom(newX, newY) && levels.getRoomToGrid(newX, newY) != null) {
            if (noun.equals("west") || noun.equals("east") || noun.equals("north") || noun.equals("south"))
            {
                playerCords.setCoordX(newX);
                playerCords.setCoordY(newY);
                Room currentRoom = levels.getRoomToGrid(newX, newY);
                System.out.println("You moved " + noun + " to " + currentRoom.getName() + ".");
            }
        }
        else
        {
            System.out.println("You can't move " + noun + ". There's no room there.");
        }

    }

    /*
    Here we are calling one method to actually create all the rooms we have built below.
    Method is called in the constructor.
     */
    public void createRooms()
    {
        createHallways();
        createBathrooms();
        createGarage();
        createKitchens();
        createLivingRooms();
        createBedrooms();

    }


    /*
    Method to create all the hallways and lay them in the right coordinates on the map.
     */
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


    /*
    Create the kitchen with the following objects of choice.
    */
    public void createKitchens()
    {
        // Create a new Item instance with the updated quantity
           // Item key = new Item(item, updatedQuantity);

        kitchen = new Room("kitchen");
        cabinet = new RoomObjects("Cabinet");
        refrigerator = new RoomObjects("Refrigerator");
        //cabinet.addItem(key);

        //this is what needs to be in the search method parser engine
        kitchen = new RoomBuilder("Kitchen")
                .setLightsOn(true)
                .addObject(cabinet)
                .addObject(refrigerator)
                .build();

        //set the desired coordinates for kitchen
        levels.setRoomToGrid(6, 3, kitchen);
    }


    /*
    Create the livingroom with the following objects of choice.
    */
    public void createLivingRooms() {
        // Initialize the living room and its objects
        livingRoom = new Room("livingroom");
        sofa = new RoomObjects("Sofa");
        tv = new RoomObjects("TV");
        lamp = new RoomObjects("Lamp");
        piano = new RoomObjects("Piano");
        table = new RoomObjects("Table");

        // Build the living room with objects
        livingRoom = new RoomBuilder("livingroom")
                .setLightsOn(true)
                .addObject(sofa)
                .addObject(tv)
                .addObject(lamp)
                .addObject(piano)
                .addObject(table)
                .build();

        // Set the living room at the desired grid coordinates
        levels.setRoomToGrid(2, 5, livingRoom);
    }



    /*
    Create the garage with the following objects of choice.
    */
    public void createGarage() {
        int playerId = 1;  // Player ID
        String item = "Key";  // The item to update ("Key")
        // Initialize the garage and its objects
        car = new RoomObjects("car");
        mower = new RoomObjects("mower");

        garage = new RoomBuilder("Garage")
                .setLightsOn(true)
                .addObject(car)
                .addObject(mower)
                .build();

        // Set the desired coordinates for garage
        levels.setRoomToGrid(7, 5, garage);
    }



    /*
    Create the bedroom with the following objects of choice.
    */
    public void createBedrooms() {
        int playerId = 1;  // Player ID
        String item = "Flashlight";  // The item to update ("Flashlight")

        // Initialize the bedroom and its objects
        bedroom = new Room("bedroom");
        bed = new RoomObjects("Bed");
        dresser = new RoomObjects("Dresser");
        vanity = new RoomObjects("Vanity");

        bedroom = new RoomBuilder("Bedroom")
                .setLightsOn(true)
                .addObject(bed)
                .addObject(dresser)
                .addObject(vanity)
                .build();

        // Set the desired coordinates for bedroom
        levels.setRoomToGrid(3, 2, bedroom);
    }


    /*
    Create the bathroom with the following objects of choice.
    */
    public void createBathrooms()
    {

        bathroom = new Room("bathroom");
        cabinet2 = new RoomObjects("Bathroom Cabinet");
        sink = new RoomObjects("Sink");
        tub = new RoomObjects("Tub");
        toilet = new RoomObjects("Toilet");

        bathroom = new RoomBuilder(

                "Bathroom")
                .setLightsOn(true)
                .addObject(cabinet2)
                .addObject(sink)
                .addObject(tub)
                .addObject(toilet)
                .build();

        //set the desired coordinates for bathroom
        levels.setRoomToGrid(5,5, bathroom);
    }


    //set up the gameTimer to call and start.
    public void setUpGameTimer()
    {
        gameTimer = new GameTimer();

        gameTimer.start();
    }


    //method to survival timer.
    public void setUpSurvivalTimer()
    {
        survivalTimer = new SurvivalTimer();
        survivalTimer.setSeconds(10);
        survivalTimer.start();
    }


    //if there are any windows.
    public Window getWindow() {
        return window;
    }


    //if there are any set windows.
    public void setWindow(Window window) {
        this.window = window;
    }


    //get the database.
    public Database getDb() {
        return db;
    }


    public void setDb(Database db) {
        this.db = db;
    }


    //all our verbs in the hashset
    public HashSet<String> getVerbs() {
        //declare a new hashset
        verbs = new HashSet<>();
        String[] verbList = {"take", "hide", "lock", "grab", "drop", "open", "exit", "go", "look", "unlock", "turn", "search", "show"};
        verbs.addAll(Arrays.asList(verbList));
        return verbs;
    }


    //all the nouns in the hashset.
    public HashSet<String> getNouns() {
        //declare a new hashset
        nouns = new HashSet<>();
        String[] nounList = {"key", "door", "room", "flashlight", "award", "upstairs", "downstairs", "drawer", "cabinet",
        "couch", "curtain", "noisemaker", "lights", "window", "fridge", "car", "sink", "desk", "bed", "stove", "shelves",
        "bookshelf", "table", "chair", "nightstand", "counter", "boxes", "timer", "north", "south", "east", "west", "kitchen",
        "bedroom", "hallway", "basement", "livingroom", "bathroom", "refrigerator", "map", "garage"};
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
        int currentY = playerCords.getCoordY();
        
        
        //validation for verb search
        if (verb.equals("search")) {

            //continue with switch statement depending on which noun or room it is from user input.
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
                    break;
                case "bedroom":
                    if(levels.getRoomToGrid(currentX, currentY) != bedroom)
                    {
                        System.out.println("You are not in the bedroom");
                    }
                    else {
                        System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchRoom());
                    }
                    break;
                case "garage":
                    if(levels.getRoomToGrid(currentX, currentY) != garage)
                    {
                        System.out.println("You are not in the garage.");
                    }
                    else {
                        System.out.println(levels.getRoomToGrid(playerCords.getCoordX(), playerCords.getCoordY()).searchRoom());
                    }
                    break;
                case "bathroom":
                    if(levels.getRoomToGrid(currentX, currentY) != bathroom)
//                    if(levels.getRoomToGrid(currentX, currentY) != bathroom && levels.getRoomToGrid(currentX, currentY) != halfbath)
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
                        System.out.println("You are not in a livingroom.");
                    }
                    else {
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


    //search cabinet method, bringing in the proper noun and verb from user input
    //this example is specific for a kitchen cabinet.
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

        return result;
    }



    //MARIANA - MUST KEEP THE DB.UPDATEQUANTITY METHOD HERE
    public void GrabItemCabinet(String noun, String verb)
    {
        //ensure that the verb is take
        if(verb.equals("take")) {
            //ensure that the noun is either key or flashlight
            if (noun.equals("key") || noun.equals("flashlight") || noun.equals("map")) {
                //ensure that the roomobject acutally has a key or flashlight
                if (cabinet.obtainCheck().equals(true)) {
                    //db has been updated for that particular player
                    db.updateQuantity(db, 1, noun);
                    //remove item from roomobject
                    System.out.println(cabinet.removeItem(noun));

                } else {
                    System.out.println("Cabinet does not have a flashlight and/or key.");
                }
            } else {
                System.out.println("You have used the wrong noun, plesee type either key or flashlight.");
            }
        }
        else
        {
            System.out.println("This verb is not in our database, Please try again.");
        }

    }


    //update query to get the item from a table roomobject
    public void GrabItemTable(String noun, String verb)
    {
        //ensure that the verb
        if(verb.equals("take")) {
            //ensure that the noun is either key or flashlight
            if (noun.equals("key") || noun.equals("flashlight") || noun.equals("map")) {
                //ensure that the roomobject acutally has a key or flashlight
                if (table.obtainCheck().equals(true)) {
                    //db has been updated for that particular player
                    db.updateQuantity(db, 1, noun);
                    //remove item from roomobject
                    System.out.println(table.removeItem(noun));

                } else {
                    System.out.println("Cabinet does not have a flashlight and/or key.");
                }
            } else {
                System.out.println("You have used the wrong noun, plesee type either key or flashlight.");
            }
        }
        else
        {
            System.out.println("This verb is not in our database, Please try again.");
        }

    }

    //method to get an item from a desser roomobject
    public void GrabItemDresser(String noun, String verb)
    {
        //ensure that the verb
        if(verb.equals("take")) {
            //ensure that the noun is either key or flashlight
            if (noun.equals("key") || noun.equals("flashlight") || noun.equals("map")) {
                //ensure that the roomobject acutally has a key or flashlight
                if (dresser.obtainCheck().equals(true)) {
                    //db has been updated for that particular player
                    db.updateQuantity(db, 1, noun);
                    //remove item from roomobject
                    System.out.println(dresser.removeItem(noun));

                } else {
                    System.out.println("Cabinet does not have a flashlight and/or key.");
                }
            } else {
                System.out.println("You have used the wrong noun, plesee type either key or flashlight.");
            }
        }
        else
        {
            System.out.println("This verb is not in our database, Please try again.");
        }

    }








    public String KeySearch(String noun, String verb, RoomObjects room, Database db, String item, int PlayerID)
    {
        String result = "";
        if(verb.equals("take"))
        {
            if(noun.equals("key"))
            {
                //if the key is present in the whatever object you're looking in
                //if the object's list contains the key or a flashlight then run the db.
                if(room.obtainCheck() == true)
                {
                    db.updateQuantity(db, PlayerID, item);
                }
                else {
                    System.out.println("No item present!");
                }

            }


        }

        return result;
    }



    //display the 2D grid map for the user when they type show map.
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

    //method to display all the verbs in an ArrayList. Method is called in the greeting and directions
    //to show player what verbs they may use.
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


    //displays all the nouns the user can use at the beginning of the game.
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