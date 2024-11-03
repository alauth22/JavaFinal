package Main;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

            try {
                //grab the one instance of the appClient
                AppClient test1 = AppClient.INSTANCE;

                //get the actual Engine to begin
                test1.getEngine();

                //try to divide by zero
                int result = 10/0;
                System.out.println("Result: " + result);

            }
            catch (ArithmeticException e) {
                //first handle the specific exception
                System.out.println("Error: Cannot dividy by zero.");

            }
            catch (Exception e) {
                //now handle any other exceptions like a catch all
                System.out.println("An unexpected error has occured: " + e.getMessage());

            }
            finally {
                //this block of code will always execute
                System.out.println("Clean up any further actions.");
            }

        }


    }


