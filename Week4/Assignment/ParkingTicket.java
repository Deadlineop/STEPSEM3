// A3 - Parking Overstay Fine Calculator
// CONCEPT: final methods — locked against overriding by any subclass
// If a "VIPParking" subclass tried to override calculateFine(), the compiler rejects it.

public class ParkingTicket {

    String vehicleNo;
    double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo      = vehicleNo;
        this.ratePerMinute  = ratePerMinute;
    }

    // final: no subclass can change this formula
    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    // final: receipt printing logic is also locked
    public final void printReceipt(int overstayMinutes) {
        System.out.println(vehicleNo + " - Fine: Rs " + calculateFine(overstayMinutes));
    }

    public static void main(String[] args) {
        String[] vehicleNos      = { "TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456" };
        double[] rates           = { 2, 2, 3, 2 };
        int[] overstayMinutes    = { 15, 0, -5, 8 };

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], rates[i]);

            if (overstayMinutes[i] <= 0) {
                System.out.println(vehicleNos[i] + " - No fine, within allotted time");
            } else {
                ticket.printReceipt(overstayMinutes[i]);
            }
        }
    }
}
