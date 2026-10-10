// A1 - Membership Field Reach Checker
// CONCEPT: Access modifier rules for LibraryMember + per-modifier grouped summary.
//
// LibraryMember field choices (justified by who actually needs access):
//   membershipPin  -- only LibraryMember itself reads/validates the PIN -> private
//   branchCode     -- other classes in the same library package use it -> default
//   finesOwed      -- premium-loyalty subclasses in other packages need it -> protected
//   displayName    -- kiosks, web, receipts everywhere read it -> public
//
// summarizeByModifier() groups the batch results BY MODIFIER rather than giving a flat total.
// Every modifier appears in the output even if it had zero attempts in this batch.

// LMFields: shows how LibraryMember's four fields are assigned their access levels.
// (Renamed LMFields here so it does not clash with the full LibraryMember in A4.)
class LMFields {
    private   String membershipPin;   // PIN stays inside the class
    String           branchCode;      // package-visible
    protected double finesOwed;       // subclass-visible across packages
    public    String displayName;     // visible everywhere

    public LMFields(String membershipPin, String branchCode,
                    double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode    = branchCode;
        this.finesOwed     = finesOwed;
        this.displayName   = displayName;
    }
}

public class LibraryMemberAccessChecker {

    // Same 4x3 access table as the Practice set (P1/P2 rules, 3 contexts here)
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    // Groups batch results per modifier.
    // Output format: "private: X allowed / Y denied | default: ... | protected: ... | public: ..."
    // ALL four modifiers appear even if none of the batch entries used that modifier.
    public static String summarizeByModifier(String[][] attempts) {
        // Counters for each modifier
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        for (String[] attempt : attempts) {
            String mod     = attempt[0];
            String result  = classifyAccess(attempt[0], attempt[1]);
            boolean allowed = result.equals("ALLOWED");

            switch (mod) {
                case "private":
                    if (allowed) privateAllowed++; else privateDenied++;   break;
                case "default":
                    if (allowed) defaultAllowed++; else defaultDenied++;   break;
                case "protected":
                    if (allowed) protectedAllowed++; else protectedDenied++; break;
                case "public":
                    if (allowed) publicAllowed++; else publicDenied++;     break;
            }
        }

        return "private: "   + privateAllowed   + " allowed / " + privateDenied   + " denied | "
             + "default: "   + defaultAllowed   + " allowed / " + defaultDenied   + " denied | "
             + "protected: " + protectedAllowed + " allowed / " + protectedDenied + " denied | "
             + "public: "    + publicAllowed    + " allowed / " + publicDenied    + " denied";
    }

    public static void main(String[] args) {
        // Individual checks
        System.out.println(classifyAccess("private",   "SAME_CLASS"));        // ALLOWED
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        System.out.println("---");

        // Batch with per-modifier grouping
        String[][] batch = {
            {"private",   "SAME_CLASS"},
            {"private",   "SAME_PACKAGE"},
            {"default",   "SAME_PACKAGE"},
            {"default",   "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public",    "DIFFERENT_PACKAGE"}
        };
        // private: 1 allowed / 1 denied | default: 1 allowed / 1 denied | protected: 2 allowed / 0 denied | public: 1 allowed / 0 denied
        System.out.println(summarizeByModifier(batch));
    }
}
