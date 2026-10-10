// M4 Assignment - Reference Copies and a Shared Exam Hall Ticket
// Same concept as IdCard from practice — reference vs object identity.
// 'copy' and 'priya' are two variables pointing to ONE object.
// 'separate' is a genuinely new object with the same content but a different address.

public class HallTicket {

    String studentName;
    int seatNumber;

    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber  = seatNumber;
    }

    public static void main(String[] args) {
        HallTicket priya = new HallTicket("Priya", 0);

        // copy points at the SAME object as priya — not a new object
        HallTicket copy = priya;
        copy.seatNumber = 45;   // changes the shared object

        // priya sees the change through its reference
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);

        // == checks memory address — same object, so true
        System.out.println("copy == priya: " + (copy == priya));

        // A brand new object — different address in memory
        HallTicket separate = new HallTicket("Priya", 45);

        // == is false — separate content is the same but it lives at a different address
        System.out.println("separate == priya: " + (separate == priya));
    }
}
