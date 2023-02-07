package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean validate(String email) {

        String regex ="^\\w+[-\\.\\w]*@(?!(?:test)\\.com$)\\w+[-\\.\\w]*?\\.\\w{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        System.out.println("Debug: validate email " + email);
        return matcher.find();
    }
}
