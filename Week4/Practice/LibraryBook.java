// M1 - Library Book Cataloguing
// CONCEPT: this() constructor chaining
// The no-ISBN constructor chains to the full constructor with "PENDING" as the default.
// This means field-setting logic exists in exactly ONE place.

public class LibraryBook {

    String title;
    String isbn;
    boolean catalogued;

    // Full constructor: used when ISBN is known
    public LibraryBook(String title, String isbn) {
        this.title     = title;
        this.isbn      = isbn;
        this.catalogued = true;
    }

    // Overloaded constructor: no ISBN yet, defaults to "PENDING"
    // this(...) MUST be the first line — it delegates to the constructor above
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
    }

    public static void main(String[] args) {
        String[] titles = { "Clean Code", "Untitled Draft", "1984", "Notes" };
        String[] isbns  = { "978-0132350884", "", "9780451524935", "" };

        // Single pass: if isbn is blank, use no-ISBN constructor; otherwise use full constructor
        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);          // chains to "PENDING"
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            book.printStatus();
        }
    }
}
