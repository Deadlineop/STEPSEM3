public class ReverseCustomerName {

    /*
     * Reverses the customer name without using StringBuilder.reverse()
     * or any built-in reverse utility — to practice manual array manipulation.
     *
     * Steps:
     *  1. Convert String to char array.
     *  2. Use two pointers (left, right) to swap characters in place.
     *  3. Convert the char array back to a String and return it.
     */
    public static String reverseCustomerName(String customerName) {
        char[] chars = customerName.toCharArray();
        int left  = 0;
        int right = chars.length - 1;

        while (left < right) {
            // Swap characters at left and right
            char temp    = chars[left];
            chars[left]  = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);   // original customerName is untouched
    }

    public static void main(String[] args) {
        String[] names = { "Sunil", "Deadlineop", "Mahesh", "Java" };

        for (String name : names) {
            String reversed = reverseCustomerName(name);
            System.out.println("Original Name : " + name);
            System.out.println("Reversed Name : " + reversed);
            System.out.println();
        }
    }
}
