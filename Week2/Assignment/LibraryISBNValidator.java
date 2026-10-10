public class LibraryISBNValidator {

    // Trim spaces and uppercase only the first 3 characters (publisher code)
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) return trimmed;

        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String rest    = trimmed.substring(3);   // year + catalog digits, untouched

        return pubCode + rest;
    }

    // Valid format: 3 letters (publisher code) + 4 digits (year) + 6 digits (catalog) = 13 chars
    public static String validateAndFormat(String code) {

        // Check total length
        if (code.length() != 13) {
            return "Invalid: wrong length (expected 13, got " + code.length() + ")";
        }

        // Check first 3 characters are all letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters are all digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must be 10 digits";
            }
        }

        // Format the output
        String pubCode     = code.substring(0, 3);
        String yearPart    = code.substring(3, 7);    // 4-digit year
        String catalogPart = code.substring(7, 13);   // 6-digit catalog number

        StringBuilder result = new StringBuilder();
        result.append("[").append(pubCode).append("]");
        result.append(" YEAR: ").append(yearPart);
        result.append(" | CATALOG: ").append(catalogPart);

        return result.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
            " pen2026004251 ",   // valid
            "12N2026004251",     // invalid: starts with digits
            " oxf2025123456 ",   // valid
            "PENGUIN20261234"    // invalid: wrong length
        };

        for (String raw : testCases) {
            String normalized = normalizeCode(raw);
            String result     = validateAndFormat(normalized);
            System.out.println("Input: \"" + raw.trim() + "\"  ->  " + result);
        }
    }
}
