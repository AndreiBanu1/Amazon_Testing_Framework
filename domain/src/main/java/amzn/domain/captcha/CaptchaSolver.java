package amzn.domain.captcha;

import com.twocaptcha.captcha.Normal;
import com.twocaptcha.TwoCaptcha;
public class CaptchaSolver {
    public static void main(String[] args) {
        String apiKey = "YOUR_2CAPTCHA_API_KEY"; // Replace this with your 2Captcha API key
        TwoCaptcha solver = new TwoCaptcha(apiKey);

        Normal captcha = new Normal();
        captcha.setFile("path/to/captcha.jpg");
        captcha.setMinLen(4);
        captcha.setMaxLen(20);
        captcha.setCaseSensitive(true);
        captcha.setLang("en");

        try {
            solver.solve(captcha);
            System.out.println("Captcha solved: " + captcha.getCode());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}

