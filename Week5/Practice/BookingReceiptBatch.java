// P5 - Immutable Booking Receipt & Nightly Settlement
// CONCEPT: true immutability with defensive copying, the "wither" pattern, instanceof dispatch.
//
// KEY IMMUTABILITY RULES:
//   1. All fields are final -- they can only be assigned once (in the constructor).
//   2. seatNumbers is an array (a reference type). Storing the reference alone is not enough --
//      the caller could still mutate the contents via their own copy.
//      FIX: copy the array ON THE WAY IN (constructor) and ON THE WAY OUT (getter).
//   3. "Changing" a receipt means returning a brand-new BookingReceipt object.
//      The original is never touched (withUpdatedSeat returns a new copy with one seat changed).
//
// NOTE on 'final class':
//   Making BookingReceipt final would prevent GroupBookingReceipt from extending it.
//   Since the nightly processor must use instanceof to tell them apart, we keep BookingReceipt
//   non-final and rely on final FIELDS for immutability -- which is the meaningful guarantee anyway.

// BookingReceipt -- immutable (all fields final, arrays defensively copied)
class BookingReceipt {
    private final String   bookingId;
    private final String[] seatNumbers;   // defensive copy held internally

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId   = bookingId;
        // DEFENSIVE COPY IN: caller keeps their array reference; we keep our own copy
        this.seatNumbers = java.util.Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    public String getBookingId() { return bookingId; }

    // DEFENSIVE COPY OUT: caller gets their own copy; mutating it never reaches our internal array
    public String[] getSeatNumbers() {
        return java.util.Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    // "Wither" pattern: returns a new receipt with one seat updated; 'this' is unchanged.
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updated = getSeatNumbers();   // defensive copy of current seats
        updated[index] = newSeat;
        return new BookingReceipt(bookingId, updated);
    }
}

// GroupBookingReceipt -- extends BookingReceipt, adds groupSize
class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() { return groupSize; }
}

// Public main class -- processes a nightly batch of mixed receipts
public class BookingReceiptBatch {

    // processNightlySettlement:
    //   - null entries are skipped (null-safe -- a flaky scanner may produce gaps)
    //   - GroupBookingReceipt entries are counted separately via instanceof
    //   - returns a summary string
    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed  = 0;
        int nullSkipped = 0;
        int group      = 0;
        int individual = 0;

        for (BookingReceipt r : receipts) {
            if (r == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            // instanceof checks the ACTUAL runtime type, not the declared type
            if (r instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        // --- defensive copy test ---
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";                       // mutating the returned copy
        System.out.println(b.getSeatNumbers()[0]); // A1 -- original is unchanged

        // --- wither pattern ---
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(b.getSeatNumbers()[1]);       // A2 -- original unchanged
        System.out.println(updated.getSeatNumbers()[1]); // A3 -- new object has the change

        System.out.println("---");

        // --- nightly settlement ---
        BookingReceipt[] batch = {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        // 2 processed | 1 null skipped | 1 group | 1 individual
        System.out.println(processNightlySettlement(batch));
    }
}
