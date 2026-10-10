// M5 - Account Batch Payments
// CONCEPT: instanceof — checking what an object actually is at runtime
// HostelFeeAccount extends FeeAccountBase.
// An array can hold both types via the parent reference.
// instanceof lets you check "is this object actually a HostelFeeAccount underneath?"
// before deciding how to process it.

// Base class
class FeeAccountBase {
    String accountId;

    public FeeAccountBase(String accountId) {
        this.accountId = accountId;
    }
}

// Subclass with a different payment behaviour
class HostelFeeAccount extends FeeAccountBase {
    public HostelFeeAccount(String accountId) {
        super(accountId);
    }
}

// Processor class — contains the batch logic
public class FeeAccountBatch {

    // instanceof decides which payment path to take
    public void processPayment(FeeAccountBase account, double amount) {
        if (account instanceof HostelFeeAccount) {
            // Hostel accounts split into two installments
            System.out.println("Paid in two installments (hostel account)");
        } else {
            // Day-scholar accounts pay in one go
            System.out.println("Paid in one go (day-scholar account)");
        }
    }

    public static void main(String[] args) {
        // Mixed array: parent type holds both HostelFeeAccount and FeeAccountBase objects
        FeeAccountBase[] accounts = {
            new HostelFeeAccount("H-001"),
            new HostelFeeAccount("H-002"),
            new FeeAccountBase("D-001"),
            new FeeAccountBase("D-002")
        };

        FeeAccountBatch processor = new FeeAccountBatch();
        int hostelCount    = 0;
        int dayScholarCount = 0;

        for (FeeAccountBase account : accounts) {
            processor.processPayment(account, 60000);

            // Count each type using instanceof
            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println("Hostel accounts processed: " + hostelCount
                         + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}
