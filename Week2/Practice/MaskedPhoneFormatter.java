public class MaskedPhoneFormatter {

    public static String maskPhoneNumber(String phone) {
        // Validate length
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validate all characters are digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Extract the last 4 digits
        String lastFour = phone.substring(6);   // characters at index 6,7,8,9

        // Build the masked number using StringBuilder
        // Start with "XXXXXX", append last 4, then insert "-" between them
        StringBuilder masked = new StringBuilder("XXXXXX");
        masked.append(lastFour);   // "XXXXXX3210"
        masked.insert(6, "-");     // "XXXXXX-3210"  (insert at position 6)

        return masked.toString();
    }

    public static void main(String[] args) {
        String[] testNumbers = { "9876543210", "98765", "12345678ab", "1234567890" };

        for (String number : testNumbers) {
            System.out.println("\"" + number + "\"  ->  " + maskPhoneNumber(number));
        }
    }
}
