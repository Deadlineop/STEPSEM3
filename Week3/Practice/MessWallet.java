// M2 - Encapsulated Mess-Card Wallet
// Key idea: balance is private. No code outside this class can say wallet.balance = 999.
// The only way in or out is through topUp(), deduct(), and getBalance().
// This is ENCAPSULATION — the class protects its own data.

public class MessWallet {

    // private means ONLY code inside MessWallet can read or write this field
    private double balance;

    // Constructor: if someone tries to open a wallet with negative money, start at 0
    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: negative opening balance. Starting at 0.");
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }
    }

    // topUp: adds money, but only if the amount makes sense
    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Balance after top-up: " + balance);
    }

    // deduct: removes money, but refuses if it would make balance negative
    public void deduct(double amount) {
        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }
        balance -= amount;
    }

    // getBalance: the ONLY public way to READ balance — there is NO setBalance()
    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);         // balance -> 700
        wallet.deduct(1000);       // rejected: 1000 > 700

        System.out.println("Final balance: " + wallet.getBalance());
    }
}
