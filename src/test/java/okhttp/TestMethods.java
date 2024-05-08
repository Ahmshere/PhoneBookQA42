package okhttp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMethods {
    public static void main(String[] args) {
        String msg = "Contact was afhngfhnghndded! ID: 45b021ae-9443-4322-8874-f8c37159d682 fgdfgbdfhnbdjgl";
        System.out.println(msg.substring(msg.lastIndexOf(" ") + 1));
    }

    public static String getId(String input) {
        Pattern pattern = Pattern.compile("ID: (\\S+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }

    }


}
