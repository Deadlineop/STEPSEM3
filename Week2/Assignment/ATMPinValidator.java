public class ATMPinValidator {

    // No loop needed here — just length() and an if/else
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("\"" + pin + "\"  ->  Invalid PIN - must be exactly 4 digits.");
        } else {
            System.out.println("\"" + pin + "\"  ->  PIN length OK.");
        }
    }

    public static void main(String[] args) {
        checkPinLength("482");      // too short
        checkPinLength("4820");     // exactly 4, valid
        checkPinLength("48201");    // too long
        checkPinLength("");         // empty
    }
}
