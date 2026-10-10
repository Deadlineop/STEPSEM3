// A5 - Immutable Loan Receipt & Nightly Circulation Ledger
// CONCEPT: final fields + defensive copying + "wither" pattern + instanceof dispatch + static block.
// This combines EVERY concept from Week 5 in one file.
//
// LoanReceipt (immutable):
//   - All fields are final (memberId and bookIds can only be assigned once).
//   - bookIds array is defensively copied IN (constructor) and OUT (getter) to prevent
//     external mutation from ever reaching the internal data.
//   - withCorrectedBookId() returns a BRAND-NEW LoanReceipt; the original is untouched.
//
// ReferenceOnlyLoanReceipt (subclass):
//   - Books that never leave the building get a roomNumber instead of a member.
//   - Extends LoanReceipt; instanceof in processNightlyCirculation() distinguishes them.
//
// CirculationLedger:
//   - static block sets the branch code ONCE when the class is first loaded.
//   - processNightlyCirculation() handles nulls safely and uses instanceof for dispatch.
//
// NOTE on 'final class':
//   Making LoanReceipt final would block ReferenceOnlyLoanReceipt from extending it.
//   Since instanceof-based polymorphism requires inheritance, we keep LoanReceipt non-final
//   and rely on final FIELDS for the immutability guarantee that matters.

// LoanReceipt -- all fields final, arrays defensively copied
class LoanReceipt {
    private final String   memberId;
    private final String[] bookIds;   // copy on the way in and on the way out

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        // DEFENSIVE COPY IN: we own our array; the caller still has theirs
        this.bookIds  = java.util.Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() { return memberId; }

    // DEFENSIVE COPY OUT: the caller gets their own array to mutate freely
    public String[] getBookIds() {
        return java.util.Arrays.copyOf(bookIds, bookIds.length);
    }

    // Wither pattern: return a new LoanReceipt with one book ID corrected
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updated = getBookIds();   // defensive copy of current bookIds
        updated[index] = newId;
        return new LoanReceipt(memberId, updated);
    }
}

// ReferenceOnlyLoanReceipt -- for books that never leave the building (room-use only)
class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;   // reading room where the book was used

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
}

// CirculationLedger -- processes the night's complete set of loan receipts
public class LoanReceiptLedger {

    // static block: fires EXACTLY ONCE when CirculationLedger class is first loaded
    // Use it for class-level one-time setup (here: the branch code for this library)
    static String branchCode;

    static {
        branchCode = "SRM-CENTRAL";
        System.out.println("Ledger initialised for branch: " + branchCode);
    }

    // processNightlyCirculation:
    //   - Null entries are skipped (the scanner sometimes sends gaps -- never crash)
    //   - ReferenceOnlyLoanReceipt entries are counted separately via instanceof
    //   - Returns a human-readable summary line
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed      = 0;
        int nullSkipped    = 0;
        int referenceOnly  = 0;
        int regular        = 0;

        for (LoanReceipt r : receipts) {
            if (r == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (r instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
             + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        // --- defensive copy test (constructor direction) ---
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";                    // mutate the returned copy
        System.out.println(r.getBookIds()[0]); // BK-100 -- original is unchanged

        // --- defensive copy test (getter direction) ---
        System.out.println("---");

        // --- wither pattern ---
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(java.util.Arrays.toString(r.getBookIds()));         // [BK-100, BK-101]
        System.out.println(java.util.Arrays.toString(corrected.getBookIds())); // [BK-100, BK-102]

        System.out.println("---");

        // --- nightly circulation ---
        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        // 2 processed | 1 null skipped | 1 reference-only | 1 regular
        System.out.println(processNightlyCirculation(batch));
    }
}
