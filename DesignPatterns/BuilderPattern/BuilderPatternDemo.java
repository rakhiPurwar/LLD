package BuilderPattern;// ============================================================
//         BUILDER DESIGN PATTERN — COMPLETE NOTES
// ============================================================

// ---- WHAT IS IT? -------------------------------------------
// A Creational Design Pattern that builds complex objects
// step by step. Instead of one giant constructor, a Builder
// class collects all pieces and assembles the final object.

// ---- WHY IS IT NEEDED? -------------------------------------
// PROBLEM 1 — Telescoping Constructor:
//   new User("Rakhi", "rakhi@mail.com", 28, null, null, true, false);
//   → Hard to read, error-prone, unclear which param is what.
//
// PROBLEM 2 — Setters leave object in inconsistent state:
//   User u = new User();
//   u.setName("Rakhi");   // object is half-built here — DANGEROUS
//   u.setEmail("...");
//
// SOLUTION — Builder:
//   → Set only what you need (optional fields can be skipped)
//   → Object is created ONLY when .build() is called
//   → Object can be made fully IMMUTABLE (no setters needed)

// ---- HOW TO IMAGINE IT? ------------------------------------
// Think of ordering a custom burger 🍔
//   .bun("Sesame")     → choose bun
//   .patty("Chicken")  → choose patty
//   .cheese(true)      → add cheese
//   .onions(false)     → skip onions
//   .build()           → burger is ready!
// The kitchen (Builder) assembles it only at the end.

// ---- WHERE TO USE IT? --------------------------------------
// ✅ Object has many fields (especially optional ones)
// ✅ You want immutable objects (no setters after creation)
// ✅ You want readable, fluent object creation
// ✅ Object creation involves validation before building
//
// Real-world examples:
//   → StringBuilder in Java
//   → HttpRequest.newBuilder()
//   → Lombok's @Builder annotation
//   → Query builders in ORMs

//How do you ensure immutability in Builder pattern?", say:
//
//"Make fields final, remove setters, make the constructor private,
// and optionally mark the class final to prevent subclasses from breaking the immutability contract."
// ============================================================
//                   FULL CODE EXAMPLE
// ============================================================

public class BuilderPatternDemo {

    // ==========================================================
    // STEP 1: The Product Class (what we want to build)
    //         All fields are FINAL → immutable after creation
    //         Constructor is PRIVATE → only Builder can create it
    // ==========================================================
    static class PaymentRequest {

        // Required fields — must always be provided
        private final String transactionId;
        private final double amount;
        private final String currency;

        // Optional fields — can be skipped
        private final String description;
        private final String callbackUrl;
        private final boolean isRecurring;
        private final String customerId;

        // Private constructor — takes Builder, copies all fields
        // This ensures object is only created via Builder
        private PaymentRequest(Builder builder) {
            this.transactionId = builder.transactionId;
            this.amount        = builder.amount;
            this.currency      = builder.currency;
            this.description   = builder.description;
            this.callbackUrl   = builder.callbackUrl;
            this.isRecurring   = builder.isRecurring;
            this.customerId    = builder.customerId;
        }

        // Getters ONLY — no setters = truly immutable object
        public String getTransactionId() { return transactionId; }
        public double getAmount()        { return amount; }
        public String getCurrency()      { return currency; }
        public String getDescription()   { return description; }
        public String getCallbackUrl()   { return callbackUrl; }
        public boolean isRecurring()     { return isRecurring; }
        public String getCustomerId()    { return customerId; }

        @Override
        public String toString() {
            return "\nPaymentRequest {" +
                "\n  transactionId : " + transactionId +
                "\n  amount        : " + amount +
                "\n  currency      : " + currency +
                "\n  description   : " + description +
                "\n  callbackUrl   : " + callbackUrl +
                "\n  isRecurring   : " + isRecurring +
                "\n  customerId    : " + customerId +
                "\n}";
        }

        // =======================================================
        // STEP 2: The Builder Class (static inner class)
        //         Lives INSIDE the product class
        //         Has same fields as product
        //         Each method sets one field and returns 'this'
        // =======================================================
        static class Builder {

            // Required fields — set via Builder constructor
            private final String transactionId;
            private final double amount;
            private final String currency;

            // Optional fields — have sensible default values
            private String description = "";
            private String callbackUrl = "";
            private boolean isRecurring = false;
            private String customerId   = "";

            // Required fields are forced through constructor
            // This ensures no PaymentRequest is built without them
            public Builder(String transactionId, double amount, String currency) {
                this.transactionId = transactionId;
                this.amount        = amount;
                this.currency      = currency;
            }

            // -----------------------------------------------
            // WHY 'return this'?
            // → 'this' refers to the current Builder object
            // → Returning it allows METHOD CHAINING
            // → Same Builder object flows through each call
            // → No new object is created at each step
            //
            // Without return this → you'd write:
            //   Builder b = new Builder("T1", 100, "USD");
            //   b.description("Payment");
            //   b.callbackUrl("https://...");
            //   PaymentRequest p = b.build();
            //
            // With return this → clean chaining:
            //   new Builder("T1", 100, "USD")
            //       .description("Payment")
            //       .callbackUrl("https://...")
            //       .build();
            // -----------------------------------------------

            public Builder description(String description) {
                this.description = description;
                return this; // returns same Builder object for chaining
            }

            public Builder callbackUrl(String callbackUrl) {
                this.callbackUrl = callbackUrl;
                return this;
            }

            public Builder isRecurring(boolean isRecurring) {
                this.isRecurring = isRecurring;
                return this;
            }

            public Builder customerId(String customerId) {
                this.customerId = customerId;
                return this;
            }

            // Final step — validate and create the actual object
            // This is the ONLY place PaymentRequest can be created
            public PaymentRequest build() {
                // Validation before building — great for catching bad data early it will not let the object build
                if (transactionId == null || transactionId.isEmpty()) {
                    throw new IllegalStateException("transactionId is required");
                }
                if (amount <= 0) {
                    throw new IllegalStateException("Amount must be greater than 0");
                }
                return new PaymentRequest(this); // passes itself to product constructor
            }
        }
    }

    // ==========================================================
    // STEP 3: Using the Builder
    // ==========================================================
    public static void main(String[] args) {

        // Only required fields — optional fields use defaults
        PaymentRequest simplePayment = new PaymentRequest.Builder(
                "TXN-001", 500.00, "USD")
                .build();

        // All fields — fluent and readable
        PaymentRequest fullPayment = new PaymentRequest.Builder(
                "TXN-002", 1500.00, "USD")
                .description("Subscription renewal")
                .callbackUrl("https://paypal.com/callback")
                .isRecurring(true)
                .customerId("CUST-789")
                .build();

        System.out.println(simplePayment);
        System.out.println(fullPayment);

        // This will throw IllegalStateException — amount is 0
        // PaymentRequest bad = new PaymentRequest.Builder("TXN-003", 0, "USD").build();
    }
}

// ============================================================
//              KEY POINTS TO REMEMBER
// ============================================================
//
// 1. Product constructor is PRIVATE — forces use of Builder
//
// 2. Product fields are FINAL — object is immutable after build
//
// 3. Builder is a STATIC INNER CLASS inside the product class
//
// 4. Required fields go in Builder's CONSTRUCTOR
//    Optional fields get DEFAULT VALUES in Builder
//
// 5. Each setter method returns 'this' (same Builder object)
//    → This is called FLUENT INTERFACE / METHOD CHAINING
//
// 6. .build() is the ONLY place the product object is created
//    → Perfect place to add VALIDATION logic
//
// 7. Builder vs Constructor:
//    Constructor → all params at once, hard to read
//    Builder     → step by step, readable, optional fields easy
//
// 8. Builder vs Setter:
//    Setter  → object can be half-built, mutable, risky
//    Builder → object only exists fully built, immutable, safe
//
// 9. Lombok shortcut (used in real projects like PayPal):
//    @Builder
//    @Getter
//    public class PaymentRequest { ... }
//    → Lombok auto-generates the entire Builder for you!

//final means "whatever ends up there is locked forever.````````````
//
// ============================================================