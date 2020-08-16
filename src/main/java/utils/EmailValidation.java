package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	public static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	public static final Pattern VALID_EMAIL_REGEX = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

	public static boolean isValidEmail(String emailId) {
		Matcher matcher = VALID_EMAIL_REGEX.matcher(emailId);
		return matcher.find();
	}
}