package Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingExample {

    //Instantiate logging object to be used by the class.
    final static Logger log = LogManager.getLogger(LoggingExample.class.getName());

    public static void main(String[] args){

        System.out.println("Hello world.");
        log.debug("Hello World!");
        log.debug("Hello World, again!");
        log.warn("Hello world!!");

        // Additional logging statements -Mariana
        System.out.println("Welcome to our game!");
        log.info("Info: Application is starting.");
        log.info("Info: Application is running.");
        log.warn("Warning: This is a warning message.");
        log.error("Error: An error has occurred when starting the game!");
    }

}

