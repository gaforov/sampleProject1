package utils;

public class GlobalUtils {

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pause interrupted: " + e.getMessage());
        }
    }

}
