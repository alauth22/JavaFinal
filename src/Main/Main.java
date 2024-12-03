package Main;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

            try {
                //grab the one instance of the appClient
                AppClient test1 = AppClient.INSTANCE;
                test1.getParserEngine().scanText();

            }
            catch (ArithmeticException e) {
                //first handle the specific exception
                System.out.println("Error: Cannot divide by zero.");

            }
            catch (Exception e) {
                //now handle any other exceptions like a catch all
                System.out.println("An unexpected error has occurred: " + e.getMessage());

            }
            finally {
                //this block of code will always execute
                System.out.println("Clean up any further actions.");
            }

        }
    }

