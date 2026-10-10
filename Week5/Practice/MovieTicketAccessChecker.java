// P1 - Movie Ticket Field Visibility Checker
// CONCEPT: Java's four access modifiers against three accessor contexts.
// Access table (the one to memorise):
//
//  Modifier    | SAME_CLASS | SAME_PACKAGE | DIFFERENT_PACKAGE
//  ------------|------------|--------------|------------------
//  private     |  ALLOWED   |   DENIED     |   DENIED
//  default     |  ALLOWED   |   ALLOWED    |   DENIED
//  protected   |  ALLOWED   |   ALLOWED    |   DENIED  (cross-pkg subclass is P2)
//  public      |  ALLOWED   |   ALLOWED    |   ALLOWED

// MovieTicket: each field gets the access level that reflects who really needs it.
//   seatNumber  -- only the booking engine reads/sets this -> private
//   screenId    -- other classes in the same cinema package need it -> default (package)
//   ticketPrice -- loyalty/discount subclasses in other packages -> protected
//   movieTitle  -- display everywhere (web, kiosk, receipt) -> public
class MovieTicket {
    private   int    seatNumber;   // engine-internal only
    int              screenId;     // package-visible
    protected double ticketPrice;  // subclass-visible across packages
    public    String movieTitle;   // visible from anywhere

    // Constructor to initialise all four fields
    public MovieTicket(int seatNumber, int screenId, double ticketPrice, String movieTitle) {
        this.seatNumber  = seatNumber;
        this.screenId    = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle  = movieTitle;
    }
}

// AccessChecker: pure logic, no MovieTicket fields involved.
// classifyAccess() encodes the 4x3 access table directly.
public class MovieTicketAccessChecker {

    // Returns "ALLOWED" or "DENIED" for a given (modifier, context) pair.
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                // private: only the same class can see it
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                // default (package-private): same class + same package
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // protected: same class + same package (cross-pkg subclass handled in P2)
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                // public: visible everywhere
                return "ALLOWED";

            default:
                return "DENIED";   // unknown modifier -- treat as most restrictive
        }
    }

    // Runs a batch of {modifier, context} pairs and returns a summary string.
    // Example output: "Allowed: 2 | Denied: 1"
    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;
        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        // --- individual checks ---
        System.out.println(classifyAccess("private",   "SAME_CLASS"));        // ALLOWED
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED
        System.out.println(classifyAccess("public",    "DIFFERENT_PACKAGE")); // ALLOWED
        System.out.println(classifyAccess("default",   "SAME_PACKAGE"));      // ALLOWED
        System.out.println(classifyAccess("default",   "DIFFERENT_PACKAGE")); // DENIED

        System.out.println("---");

        // --- batch ---
        String[][] batch = {
            {"default",   "SAME_PACKAGE"},
            {"default",   "DIFFERENT_PACKAGE"},
            {"public",    "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch));  // Allowed: 2 | Denied: 1
    }
}
