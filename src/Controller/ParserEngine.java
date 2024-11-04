package Controller;
import java.util.Set;

public class ParserEngine {

    //create private sets for nouns and verbs so that they remain unique.
    //could try an array or a dictionary, but I don't care about order.
    private Set<String> verbs;
    private Set<String> nouns;


    //constructor
    public ParserEngine() {

        //add to verbs set.
        verbs.add("take");
        verbs.add("hide");
        verbs.add("lock");
        verbs.add("run");
        verbs.add("grab");
        verbs.add("pick");
        verbs.add("drop");
        verbs.add("open");
        verbs.add("exit");

        //add to nouns set.
        nouns.add("key");
        nouns.add("door");
        nouns.add("room");
        nouns.add("flashlight");
        nouns.add("award");
        nouns.add("upstairs");
        nouns.add("downstairs");

    }



    //method to take string input and split the words into an array.
    public void parseInput(String input) {

        //declare a string array to hold all the words
        //set input to lower and split by space.
        String[] wordsInput = input.split(" ");

        //need to ensure that the command has at least two words (a noun and a verb) that are in our sets!!!!!
        //NEED TO FIX THIS SINCE I ASSUME THAT THE VERB COMES FIRST AND THE NOUN COMES SECOND
        if (wordsInput.length >= 2) {

            //AGAIN I ASSUME THE FIRST VALUE IN THE ARRAY IS A VERB. NEED TO FIX THIS.
            String currentVerb = wordsInput[0];
            String currentNoun = wordsInput[1];

            //Check if the words are in our pre-made sets.
            if (verbs.contains(currentVerb) && nouns.contains(currentNoun)) {

                //logic do something with the correct noun and verb.
            }
            else {
                System.out.println("Current command is not in our system. Please try a different command.");
            }


        }
        //if our words are less than 2, then user needs a new command.
        else {
            System.out.println("This is an incomplete command. Please try again!");
        }

    }


    //method with switch statement depending on the verb.
    //DON'T LIKE THIS, WANT EACH VERB TO HAVE IT'S OWN METHOD HERE.
    public void VerbNoun (String verb, String noun) {
        switch(verb) {
            case "take":
                System.out.println("You take the " + noun + " to the house.");
                break;

            case "hide":
                System.out.println("You can hide in the " + noun + ". Be on the lookout for the buglar. ");
                break;

            case "lock":
                System.out.println("You take the " + noun + " and unlock the current door.");
                break;

            //rest of the logic for the remaining verbs.


        }



    }


    public static void main(String[] args) {

        //call the scanner
        //get input
        //call the necessary class.

    }



}
