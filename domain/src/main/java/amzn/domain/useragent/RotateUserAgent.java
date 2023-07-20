package amzn.domain.useragent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RotateUserAgent {
    private static List<String> userAgents = new ArrayList<>();
    static {
        // Add multiple User-Agent strings to the list
        userAgents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        userAgents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0");
        // Add more User-Agent strings here...
    }
    public static String getRandomUserAgent() {
        // Randomly select a User-Agent from the list
        Random random = new Random();
        return userAgents.get(random.nextInt(userAgents.size()));
    }
}
