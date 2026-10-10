// A4 - Library Membership Card Setup
// CONCEPT: static block
// static block fires ONCE when MembershipCard class is first loaded.
// No matter how many cards you create in the loop, "Library info loaded" prints only once.

public class MembershipCard {

    // Static fields: same for every card
    static String libraryName;
    static String validUntil;

    // Instance field: each card has a different student name
    String studentName;

    // static block: runs exactly once, before the first object is created
    static {
        libraryName = "SRM Central Library";
        validUntil  = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + studentName);
    }

    public static void main(String[] args) {
        String[] names = { "Ananya", "Rohan", "Priya", "Arjun", "Sneha" };

        // No matter how many iterations, the static block above runs only once
        for (String name : names) {
            new MembershipCard(name);
        }
    }
}
