// A3 - Book Copy Circulation Guard
// CONCEPT: private fields with validated public methods -- boundary-safe state.
//
// The invariant that must ALWAYS hold: 0 <= copiesAvailable <= copiesTotal
// Every method checks this BEFORE changing state. Violations are silently rejected.
//
//   checkOut()  -- decrements if copies are available; if copiesAvailable == 0, do nothing
//   checkIn()   -- increments if we are below capacity; if copiesAvailable == copiesTotal, do nothing
//
// No setter for copiesAvailable exists anywhere -- the only way to change it is through
// checkOut() and checkIn(), which enforce the invariant at every step.

public class BookInventoryGuard {

    private int copiesTotal;      // total physical copies the branch owns
    private int copiesAvailable;  // copies currently on the shelf (not checked out)

    public BookInventoryGuard(int copiesTotal) {
        // Guard: zero or negative total makes no sense for a real book entry
        if (copiesTotal <= 0) {
            System.out.println("Invalid copies total -- inventory not created");
            return;
        }
        this.copiesTotal     = copiesTotal;
        this.copiesAvailable = copiesTotal;  // all copies on shelf at the start
    }

    // Check out one copy. If no copy is free, the call is silently ignored.
    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
        // else: nothing to give -- silent rejection
    }

    // Return one copy. If shelf is already full (all copies are in), ignore silently.
    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
        // else: nothing genuine to return -- silent rejection
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        // Test 1: exhaust all copies, then a 4th checkOut is silently dropped
        BookInventoryGuard b = new BookInventoryGuard(3);
        b.checkOut(); b.checkOut(); b.checkOut();
        b.checkOut();                              // 4th attempt -- silently rejected
        System.out.println(b.getCopiesAvailable()); // 0

        System.out.println("---");

        // Test 2: check everything back in, then 4th checkIn is silently dropped
        b.checkIn(); b.checkIn(); b.checkIn();
        b.checkIn();                               // already full -- silently rejected
        System.out.println(b.getCopiesAvailable()); // 3

        System.out.println("---");

        // Test 3: mixed workflow
        BookInventoryGuard shelf = new BookInventoryGuard(5);
        shelf.checkOut(); shelf.checkOut();        // 2 out, 3 on shelf
        System.out.println(shelf.getCopiesAvailable()); // 3
        shelf.checkIn();                           // 1 returned, 4 on shelf
        System.out.println(shelf.getCopiesAvailable()); // 4
    }
}
