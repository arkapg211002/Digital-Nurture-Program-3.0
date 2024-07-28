public class SingletonTest {
    public static void main(String[] args) {
        // Get the single instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        System.out.println();

        // Check if both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("Logger instances are the same.");
        } else {
            System.out.println("Logger instances are different.");
        }
        
        // Test logging
        logger1.log("This is a test log message.");
        logger2.log("This is another test log message.");
    }
}

