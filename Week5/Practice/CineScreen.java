// P3 - Seat Booking Encapsulation Guard
// CONCEPT: private fields with validated public methods ("one door in, one door out").
//   seatsTotal and seatsAvailable are private -- no one outside can set them directly.
//   Every change goes through bookSeat() or cancelBooking(), which enforce the invariant:
//       0 <= seatsAvailable <= seatsTotal
//
// Invalid transitions are silently rejected (no crash, no message), which is the
// standard pattern for boundary-safe state: the caller is simply told nothing happened.

public class CineScreen {

    private int seatsTotal;      // total capacity -- never changes after construction
    private int seatsAvailable;  // current free seats -- only moves through the two methods

    // Constructor rejects a zero-or-negative seatsTotal immediately.
    // A bad value would make the invariant impossible to guarantee.
    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            // Print a clear message and leave both fields at 0 (object is unusable).
            System.out.println("construction rejected");
            return;
        }
        this.seatsTotal     = seatsTotal;
        this.seatsAvailable = seatsTotal;  // all seats free at the start
    }

    // Book one seat. If no seat is free, do nothing (silent rejection).
    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
        // else: already full -- silently ignore
    }

    // Cancel one booking. If screen is already completely empty of bookings, do nothing.
    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
        // else: already at full capacity -- silently ignore
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {
        // Test 1: construction rejected
        new CineScreen(0);    // prints "construction rejected"

        System.out.println("---");

        // Test 2: three bookings on a 2-seat screen -> third is silently dropped
        CineScreen c = new CineScreen(2);
        c.bookSeat(); c.bookSeat(); c.bookSeat();   // third rejected
        System.out.println(c.getSeatsAvailable());  // 0

        System.out.println("---");

        // Test 3: three cancellations when only 2 were booked -> third is silently dropped
        c.cancelBooking(); c.cancelBooking(); c.cancelBooking();  // third rejected
        System.out.println(c.getSeatsAvailable());  // 2  (back to full capacity)
    }
}
