package amzn.domain.proxies;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RotateProxy {
    private List<String> proxies;
    private int currentProxyIndex;

    public RotateProxy() {
        fetchProxies(); // Fetch proxies when the RotateProxy is instantiated
        currentProxyIndex = 0; // Set the initial index to 0
    }
    public void fetchProxies() {
        proxies = new ArrayList<>();

        try {
            InputStream inputStream = getClass().getResourceAsStream("/proxy_file.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(streamReader);

            String line;
            while ((line = br.readLine()) != null) {
                proxies.add(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error reading proxy_file.txt");
        }

        // Logging the number of fetched proxies
        System.out.println("Fetched " + proxies.size() + " proxies.");

        if (proxies.isEmpty()) {
            System.out.println("No proxies available");
            throw new IllegalStateException("No proxies available");
        }
    }
    public String getCurrentProxy() {
        return proxies.get(currentProxyIndex);
    }
    public String getRandomProxy() {
        if (!proxies.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(proxies.size());
            return proxies.get(randomIndex);
        } else {
            throw new IllegalStateException("No proxies available");
        }
    }
}
