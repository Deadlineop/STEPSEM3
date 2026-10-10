// M2 Assignment - Encapsulated Payroll Account
// basicSalary and bonus are private. getNetSalary() is the only way to read them combined.
// No public setter exists — the only way to change these values is through
// creditBonus() and deductTax(), which both validate their inputs.

public class PayrollAccount {

    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: negative salary. Starting at 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0;
    }

    // Adds bonus, but rejects zero or negative amounts
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus rejected: amount must be positive.");
            return;
        }
        bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    // Reduces basicSalary by a percentage; percent must be in [0, 100]
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Tax rejected: percent must be between 0 and 100.");
            return;
        }
        double deduction = basicSalary * (percent / 100);
        basicSalary -= deduction;
        System.out.println("Tax deducted: " + (int) percent + "%");
    }

    // Read-only access to the combined salary — no setters exist
    public double getNetSalary() {
        return basicSalary + bonus;
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);      // bonus -> 5000
        account.deductTax(10);          // basicSalary -> 45000

        System.out.println("Net salary: Rs " + account.getNetSalary());  // 45000 + 5000 = 50000
    }
}
