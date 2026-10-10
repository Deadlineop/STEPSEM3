// A5 - Canteen Closing-Time Payment Dispatch
// CONCEPT: instanceof with inheritance
// CardPayment extends Payment. Both live in the same Payment[] array.
// instanceof checks the ACTUAL type at runtime so we can apply the card fee only when needed.
// The running total accumulates the real charged amount (including any fee).

// Base class: cash payment
class Payment {
    public void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

// Subclass: card payment adds a 2% processing fee
class CardPayment extends Payment {
    public void payWithProcessingFee(double amount) {
        double total = amount * 1.02;   // 2% fee added
        System.out.printf("Charged (card, incl. fee): Rs %.1f%n", total);
    }
}

// Processor and main class
public class PaymentBatch {

    // Uses instanceof to dispatch the right payment method
    // Returns the actual amount charged so the caller can track total
    public double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            // Safe cast: we KNOW it's a CardPayment because instanceof confirmed it
            CardPayment cardPayment = (CardPayment) payment;
            cardPayment.payWithProcessingFee(amount);
            return amount * 1.02;   // card amount includes fee
        } else {
            payment.pay(amount);
            return amount;           // cash: no fee
        }
    }

    public static void main(String[] args) {
        // Mixed array: Payment reference can hold either Payment or CardPayment
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = { 100, 50, 200, 75, 120 };

        PaymentBatch batch = new PaymentBatch();
        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {
            totalCollected += batch.processTransaction(payments[i], amounts[i]);
        }

        System.out.printf("Total Collected: Rs %.1f%n", totalCollected);
    }
}
