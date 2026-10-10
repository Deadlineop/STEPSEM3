// A2 - Reference Desk Subclass Reach
// CONCEPT: protected's cross-package subclass rule + early-exit "first denied" scan.
//
// Extends the 3-context classifier to 5 contexts:
//   SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE   -- protected ALLOWED (accessing via own reference type)
//   SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE -- protected DENIED (accessing via parent reference type)
//
// firstDeniedAttempt() scans in order and STOPS the moment it finds a denied attempt.
// It does NOT collect all results first and then search -- the early-exit is the point.
// This mimics a compiler that wants to stop and report the FIRST problem.
//
// Full 4x5 access table:
//  Modifier  | SAME_CLASS | SAME_PKG | DIFF_PKG | SUB_OWN  | SUB_PARENT
//  ----------|------------|----------|----------|----------|----------
//  private   |  ALLOWED   |  DENIED  |  DENIED  |  DENIED  |  DENIED
//  default   |  ALLOWED   |  ALLOWED |  DENIED  |  DENIED  |  DENIED
//  protected |  ALLOWED   |  ALLOWED |  DENIED  |  ALLOWED |  DENIED
//  public    |  ALLOWED   |  ALLOWED |  ALLOWED |  ALLOWED |  ALLOWED

public class ReferenceDesk {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                // ALLOWED for: same class, same package, OR subclass in diff pkg via OWN reference
                return (accessorContext.equals("SAME_CLASS")                             ||
                        accessorContext.equals("SAME_PACKAGE")                           ||
                        accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    // Scans the list in order. Returns a description of the first denied attempt,
    // or "None Denied" if every attempt is allowed.
    // Format: "<modifier> via <context> (attempt #<1-based index>)"
    public static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            String modifier = attempts[i][0];
            String context  = attempts[i][1];
            if (classifyAccess(modifier, context).equals("DENIED")) {
                // Stop immediately -- this is the first denial
                return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        // Test 1: first attempt is allowed, second is denied (protected via PARENT_TYPE)
        String[][] batch1 = {
            {"public",    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},  // allowed
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},  // DENIED <- first
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}      // allowed (never reached)
        };
        // protected via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE (attempt #2)
        System.out.println(firstDeniedAttempt(batch1));

        System.out.println("---");

        // Test 2: all allowed -> None Denied
        String[][] batch2 = {
            {"public",    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(firstDeniedAttempt(batch2));  // None Denied

        System.out.println("---");

        // Test 3: individual cross-package checks
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // ALLOWED
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // DENIED
        System.out.println(classifyAccess("default",   "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // DENIED
    }
}
