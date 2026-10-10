public class BankTransactionValidator {

    // Step 1 — Trim spaces and uppercase only the first 3 characters (bank code)
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) return trimmed;  // too short to normalize safely

        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest     = trimmed.substring(3);     // leave digits untouched

        return bankCode + rest;
    }

    // Step 2 — Validate the normalized string and format it for display
    // Valid format: 3 letters (bank code) + 6 digits (ddMMyy) + 5 digits (seq) = 14 chars total
    public static String validateAndFormat(String reference) {

        // Check total length
        if (reference.length() != 14) {
            return "Invalid: wrong length (expected 14, got " + reference.length() + ")";
        }

        // Check first 3 characters are all letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are all digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must be 11 digits";
            }
        }

        // All checks passed. Now format the output.
        String bankCode = reference.substring(0, 3);
        String datePart = reference.substring(3, 9);    // ddMMyy (6 digits)
        String seqPart  = reference.substring(9, 14);   // sequence (5 digits)

        String dd = datePart.substring(0, 2);
        String mm = datePart.substring(2, 4);
        String yy = datePart.substring(4, 6);

        StringBuilder result = new StringBuilder();
        result.append("[").append(bankCode).append("]");
        result.append(" DATE: ").append(dd).append("/").append(mm).append("/").append(yy);
        result.append(" | SEQ: ").append(seqPart);

        return result.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
            " hdf03022600042 ",   // valid: HDF, date 03/02/26, seq 00042
            "12F03022600042",     // invalid: bank code starts with digits
            " sbi15082500001 ",   // valid: SBI, date 15/08/25, seq 00001
            "HDFC030226001"       // invalid: 13 chars, wrong length
        };

        for (String raw : testCases) {
            String normalized = normalizeReference(raw);
            String result     = validateAndFormat(normalized);
            System.out.println("Input: \"" + raw.trim() + "\"  ->  " + result);
        }
    }
}
