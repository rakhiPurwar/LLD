package ISP;

/*
╔════════════════════════════════════════════════════════════════════════════╗
║        INTERFACE SEGREGATION PRINCIPLE (ISP)                              ║
║        "Clients should not be forced to depend on interfaces              ║
║         they don't use"                                                    ║
╚════════════════════════════════════════════════════════════════════════════╝

WHY ISP MATTERS?
────────────────
❌ WITHOUT ISP: One LARGE interface forces ALL classes to implement
                 methods they DON'T use

   interface PaymentMethod {
       void pay();        // All methods
       void refund();     // required for
       void convertEMI(); // ALL classes
       void transfer();
   }

   Problems:
   • UPI forced to implement refund, EMI, transfer (doesn't support them!)
   • Empty/dummy implementations = wasted code
   • Tightly coupled = harder to change
   • Violates Liskov Substitution Principle


✅ WITH ISP: Break into SMALL, FOCUSED interfaces
             Each class implements ONLY what it needs

   interface Payable { void pay(); }
   interface Refundable { void refund(); }
   interface EMIConvertible { void convertEMI(); }
   interface InternationalTransferable { void transfer(); }

   Benefits:
   • UPI: implements only Payable ✅
   • Wallet: implements Payable + Refundable ✅
   • Card: implements all 4 ✅
   • Loosely coupled
   • Easy to extend with new features


REAL-WORLD ANALOGY:
───────────────────
🏥 Hospital has doctors:
   ❌ BAD: Every doctor must know Surgery, Medicine, Psychiatry, Pediatrics
   ✅ GOOD: Doctors specialize - some do surgery, some do medicine, etc.


EXAMPLE BREAKDOWN:
──────────────────
1️⃣ INTERFACES (Small & Focused):
   • Payable: Only makePayment()
   • Refundable: Only refundPayment()
   • EMIConvertible: Only convertToEMI()
   • InternationalTransferable: Only internationalTransfer()

2️⃣ IMPLEMENTATIONS (Each picks what it needs):
   • UPIPayment: implements Payable (basic payment only)
   • WalletPayment: implements Payable, Refundable
   • CreditCardPayment: implements ALL 4

3️⃣ SERVICE (Depends on what it needs):
   • CheckoutService.processOrder() → needs Payable
   • CheckoutService.processEMI() → needs EMIConvertible
   • No unnecessary dependencies!

════════════════════════════════════════════════════════════════════════════
*/

// ============================================
// Step 1: Break into small focused interfaces
// ============================================


interface EMIConvertible {
    void convertToEMI(int months);
}

interface InternationalTransferable {
    void internationalTransfer(String country);
}

interface Refundable {
    void refundPayment(double amount);
}


interface Payable {
    void makePayment(double amount);
}

// ============================================
// Step 2: Payment Method Implementations
// Each class only implements what it needs
// ============================================

// 🔷 UPI: ONLY supports basic payment
// Implements ONLY Payable interface (not Refundable, EMI, etc.)
class UPIPayment implements Payable {
    @Override
    public void makePayment(double amount) {
        System.out.println("UPI Payment: ₹" + amount);
    }
}

// 🟢 Wallet: Supports payment + refunds
// Implements ONLY Payable + Refundable (not EMI or international)
class WalletPayment implements Payable, Refundable {
    @Override
    public void makePayment(double amount) {
        System.out.println("Wallet Payment: ₹" + amount);
    }
    @Override
    public void refundPayment(double amount) {
        System.out.println("Wallet Refund: ₹" + amount);
    }
}

// 🔴 Credit Card: Supports EVERYTHING
// Implements ALL 4 interfaces (most powerful payment method)
class CreditCardPayment implements Payable, Refundable, EMIConvertible, InternationalTransferable {
    @Override
    public void makePayment(double amount) {
        System.out.println("Card Payment: ₹" + amount);
    }
    @Override
    public void refundPayment(double amount) {
        System.out.println("Card Refund: ₹" + amount);
    }
    @Override
    public void convertToEMI(int months) {
        System.out.println("Converted to " + months + " month EMI");
    }
    @Override
    public void internationalTransfer(String country) {
        System.out.println("Transfer to: " + country);
    }
}

// ============================================
// Step 3: Service Layer
// Only depends on what it needs (ISP in action!)
// ============================================

class CheckoutService {
    // 📌 processOrder() only needs Payable interface
    // Works with ANY payment method that can pay
    // (UPI, Wallet, Card - doesn't matter)
    public void processOrder(Payable payment, double amount) {
        payment.makePayment(amount);
    }

    // 📌 processEMI() only needs EMIConvertible interface
    // Works with ANY payment method that supports EMI
    // (Not called for UPI - makes sense!)
    public void processEMI(EMIConvertible payment, int months) {
        payment.convertToEMI(months);
    }
}

// ============================================
// Main Demo
// ============================================

public class ISP {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();

        UPIPayment upi = new UPIPayment();
        CreditCardPayment card = new CreditCardPayment();
        WalletPayment wallet = new WalletPayment();

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║         ISP EXAMPLE: PAYMENT METHODS                   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        // 1️⃣ UPI: Only can make payment
        System.out.println("1️⃣  UPI Payment (implements only Payable):");
        checkout.processOrder(upi, 500);
        System.out.println();

        // 2️⃣ Credit Card: Full featured
        System.out.println("2️⃣  Credit Card Payment (implements all interfaces):");
        checkout.processOrder(card, 5000);
        checkout.processEMI(card, 6);
        System.out.println();

        // 3️⃣ Wallet: Payment + Refund
        System.out.println("3️⃣  Wallet Payment (implements Payable + Refundable):");
        checkout.processOrder(wallet, 1000);
        System.out.println();

        // ISP Explanation
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              WHY ISP WORKS HERE?                       ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("✅ UPI implements ONLY Payable (what it supports)");
        System.out.println("✅ Wallet implements Payable + Refundable (what it needs)");
        System.out.println("✅ Card implements ALL 4 (rich features)");
        System.out.println();
        System.out.println("🎯 Result: Each class has ONLY the methods it uses");
        System.out.println("🎯 No dummy implementations, no wasted code!");
        System.out.println("🎯 Easy to add new payment methods = just pick interfaces");
    }
}