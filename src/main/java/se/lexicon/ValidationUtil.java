package se.lexicon;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public final class ValidationUtil {

    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();
    private static final RegexValidator PHONE_CHAR_VALIDATOR =
            new RegexValidator("^[0-9()+\\-\\s]+$");

    private ValidationUtil() {
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_VALIDATOR.isValid(email.trim());
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        String trimmedPhoneNumber = phoneNumber.trim();
        if (trimmedPhoneNumber.isEmpty() || !PHONE_CHAR_VALIDATOR.isValid(trimmedPhoneNumber)) {
            return false;
        }

        int digitCount = 0;
        for (char character : trimmedPhoneNumber.toCharArray()) {
            if (Character.isDigit(character)) {
                digitCount++;
            }
        }

        return digitCount >= 7;
    }

    public static boolean isValidContact(String name, String email, String phoneNumber) {
        return isValidName(name) && isValidEmail(email) && isValidPhoneNumber(phoneNumber);
    }

}
