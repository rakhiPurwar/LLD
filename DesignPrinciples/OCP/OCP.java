package OCP;/*OCP.OCP: Open/Closed Principle

Meaning: Your code should be open for extension (add new features)
 but closed for modification (don’t keep editing old code every time).

 class OCP.PaymentService {

    public void pay(String method, double amount) {

        if (method.equalsIgnoreCase("CARD")) {
            System.out.println("Paid " + amount + " using Card");

        } else if (method.equalsIgnoreCase("UPI")) {
            System.out.println("Paid " + amount + " using UPI");

        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
}

public class OCPWrong {
    public static void main(String[] args) {
        OCP.PaymentService service = new OCP.PaymentService();
        service.pay("CARD", 500);
        service.pay("UPI", 200);
    }
}

✅ Why this is wrong

Every time a new payment method comes:

You modify OCP.PaymentService

You keep adding else if

Risk: You break existing logic

Testing becomes harder (one big method)

This is not closed for modification

Fix Strategy

Use polymorphism:

Create an interface OCP.PaymentMethod

Each payment method is a separate class

Now to add a new method, you add a new class — you don’t touch existing code.
 */


interface PaymentMethod {
    void pay(double amount);
}

class CardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card");
    }
}

class UPIPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

// Later you can add new methods without modifying old code
class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class PaymentService {
    public void pay(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount);
    }
}

public class OCP {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.pay(new CardPayment(), 500);
        service.pay(new UPIPayment(), 200);

        // New feature added without modifying OCP.PaymentService:
        service.pay(new PayPalPayment(), 999);
    }
}