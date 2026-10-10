// M3 - Late Fees: Skip the On-Time Accounts
// CONCEPT: 'final' methods — cannot be overridden by any subclass
// If a subclass tried to override calculateLateFee(), the compiler would throw an error.
// This guarantees the fee formula is locked — no "VIP subclass" can quietly change it.

public class FeeAccount {

    String regNo;
    double totalFee;
    double lateFeeRate = 0.10;   // 10% late fee rate

    public FeeAccount(String regNo, double totalFee) {
        this.regNo    = regNo;
        this.totalFee = totalFee;
    }

    // 'final' means: no subclass can override this method
    public final double calculateLateFee(int daysLate) {
        return totalFee * lateFeeRate;   // simple flat 10% of total fee
    }

    // Also final: the summary print logic is locked too
    public final void printSummary(int daysLate) {
        double lateFee = calculateLateFee(daysLate);
        System.out.println(regNo + " | Total Fee: Rs " + totalFee + " | Late Fee: Rs " + lateFee);
    }

    public static void main(String[] args) {
        String[] regNos    = { "RA001", "RA002", "RA003", "RA004" };
        double[] totalFees = { 200000, 150000, 180000, 220000 };
        int[] daysLate     = { 10, 0, -2, 5 };

        for (int i = 0; i < regNos.length; i++) {
            FeeAccount account = new FeeAccount(regNos[i], totalFees[i]);

            // Skip accounts that are on time or have no valid late days
            if (daysLate[i] <= 0) {
                System.out.println(regNos[i] + " - On time, no late fee");
            } else {
                account.printSummary(daysLate[i]);
            }
        }
    }
}
