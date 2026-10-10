// P2 - Subclass Ticket Access
// CONCEPT: protected's genuinely subtle cross-package subclass rule.
//
// The tricky case everyone gets wrong:
//   PremiumMovieTicket (different package, extends MovieTicket) has two ways
//   to hold a MovieTicket -- as its OWN TYPE or as the PARENT TYPE.
//
//   PremiumMovieTicket p = new PremiumMovieTicket(...);
//   p.ticketPrice   -- ALLOWED  (OWN_TYPE: PremiumMovieTicket reference -> own inherited member)
//
//   MovieTicket m = new PremiumMovieTicket(...);
//   m.ticketPrice   -- DENIED   (PARENT_TYPE: reference is MovieTicket, compile-time type decides)
//
// Rule: Java uses the *declared (compile-time) type* of the reference, not the runtime object.
//
// Full 4x5 access table:
//  Modifier  | SAME_CLASS | SAME_PKG | DIFF_PKG | SUB_DIFF_OWN | SUB_DIFF_PARENT
//  ----------|------------|----------|----------|--------------|----------------
//  private   |  ALLOWED   |  DENIED  |  DENIED  |   DENIED     |   DENIED
//  default   |  ALLOWED   |  ALLOWED |  DENIED  |   DENIED     |   DENIED
//  protected |  ALLOWED   |  ALLOWED |  DENIED  |   ALLOWED    |   DENIED   <- key row
//  public    |  ALLOWED   |  ALLOWED |  ALLOWED |   ALLOWED    |   ALLOWED

public class SubclassTicketAccess {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                // Only the defining class itself
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                // Same class or same package -- nothing crosses the package boundary
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // Same class, same package, OR a subclass in a different package
                // accessing via its OWN TYPE reference -- not via a parent-type reference
                return (accessorContext.equals("SAME_CLASS")                             ||
                        accessorContext.equals("SAME_PACKAGE")                           ||
                        accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                // Visible everywhere
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static void main(String[] args) {
        // The two new contexts in action:
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // ALLOWED
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // DENIED

        // private and default are always DENIED for the new contexts:
        System.out.println(classifyAccess("private",   "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // DENIED
        System.out.println(classifyAccess("default",   "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // DENIED

        // public is always ALLOWED:
        System.out.println(classifyAccess("public",    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // ALLOWED
    }
}
