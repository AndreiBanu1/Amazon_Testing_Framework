package amzn.domain.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginCredentials {
    private String username;
    private String password;

    public LoginCredentials() {
        loadCredentialsFromFile();
    }

    private void loadCredentialsFromFile() {
        Path credentialsFilePath = Paths.get("credentials.txt");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(credentialsFilePath.toString())) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("=");
                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();
                            if (key.equalsIgnoreCase("username")) {
                                username = value;
                            } else if (key.equalsIgnoreCase("password")) {
                                password = value;
                            }
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Getters and setters for username and password
    public String getUsername() {
        return username;
    }
    public String getPassword() {return password;}
}
