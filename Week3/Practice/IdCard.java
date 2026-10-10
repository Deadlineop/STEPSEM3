// M4 - Reference Copies and a Shared ID Card
// KEY CONCEPT: In Java, objects live in heap memory. A variable holds a REFERENCE (address)
// not the object itself. When you write: IdCard duplicate = ravi;
// BOTH variables point to the SAME object in memory.
// Changing it through 'duplicate' changes what 'ravi' sees — they are the same thing.
// == on objects checks if both variables point to the SAME object, not if the content is equal.

public class IdCard {

    String name;
    int booksIssued;

    public IdCard(String name, int booksIssued) {
        this.name         = name;
        this.booksIssued  = booksIssued;
    }

    public static void main(String[] args) {
        // Object 1: Ravi's card
        IdCard ravi = new IdCard("Ravi", 0);

        // duplicate is NOT a new object. It's a second variable pointing at ravi's object.
        IdCard duplicate = ravi;

        // Changing through 'duplicate' changes the SAME object ravi is looking at
        duplicate.booksIssued = 3;

        // ravi sees the change because it's the same object
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);

        // == is true because both variables point to the SAME object in memory
        System.out.println("duplicate == ravi: " + (duplicate == ravi));

        // A brand new object with the same content — different place in memory
        IdCard separate = new IdCard("Ravi", 3);

        // == is false because separate is a DIFFERENT object (different address)
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}
