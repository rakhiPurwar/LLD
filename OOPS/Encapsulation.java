/*
 * ENCAPSULATION IN JAVA
 * ====================
 *
 * WHAT IS ENCAPSULATION?
 * Bundling data (variables) and methods (functions) together in a single unit (class)
 * and hiding the internal details from the outside world. It's like a capsule that
 * contains everything needed but controls what others can access.
 *
 * Real-world example: Phone - You interact with buttons and screen (public interface),
 * but don't see the circuit board, processor, or wiring (private implementation).
 *
 * CORE PRINCIPLE:
 * Data Hiding + Controlled Access = Encapsulation
 *
 * WHY IS ENCAPSULATION IMPORTANT?
 * ===============================
 * 1. DATA PROTECTION: Control who can read/modify data
 * 2. VALIDATION: Validate data before storing it
 * 3. FLEXIBILITY: Change implementation without affecting external code
 * 4. MAINTAINABILITY: Easy to modify internal logic
 * 5. SECURITY: Prevent unauthorized access to sensitive data
 * 6. CONSISTENCY: Ensure objects remain in valid state
 *
 * ACCESS MODIFIERS IN JAVA
 * =========================
 *
 * 1. PUBLIC (public)
 *    - Accessible everywhere (same package, different package, subclass)
 *    - Most open access level
 *    - Use for: Methods that define class behavior
 *    Example: public void display() { }
 *
 * 2. PRIVATE (private)
 *    - Accessible only within the same class
 *    - Most restrictive access level
 *    - Use for: Internal data and helper methods
 *    Example: private String password;
 *
 * 3. PROTECTED (protected)
 *    - Accessible within same package and subclasses
 *    - Middle ground between public and private
 *    - Use for: Methods/data that subclasses need to use
 *    Example: protected void initialize() { }
 *
 * 4. DEFAULT / PACKAGE-PRIVATE (no modifier)
 *    - Accessible only within the same package
 *    - Use for: Internal classes and methods within a package
 *    Example: void helperMethod() { }
 *
 * ACCESS MODIFIER SUMMARY:
 * =======================
 *             public | protected | package | private
 * Same class    YES      YES        YES       YES
 * Same package  YES      YES        YES       NO
 * Subclass      YES      YES        NO        NO
 * Different pkg YES      NO         NO        NO
 *
 * GETTERS AND SETTERS (Accessors and Mutators)
 * ============================================
 *
 * GETTER (Accessor):
 * - Method to READ/GET the value of a private variable
 * - Naming convention: get + VariableName()
 * - Example: public String getName() { return name; }
 *
 * SETTER (Mutator):
 * - Method to WRITE/SET the value of a private variable
 * - Includes VALIDATION logic
 * - Naming convention: set + VariableName()
 * - Example: public void setAge(int age) {
 *               if (age > 0 && age < 150) this.age = age;
 *            }
 *
 * BENEFITS OF GETTERS/SETTERS:
 * - Add validation before storing data
 * - Read-only properties (getter only)
 * - Write-only properties (setter only)
 * - Change implementation without affecting users
 * - Add logging, notifications, or side effects
 *
 * ENCAPSULATION BEST PRACTICES
 * ============================
 *
 * 1. MAKE VARIABLES PRIVATE
 *    private int age;
 *    private String email;
 *
 * 2. PROVIDE PUBLIC GETTER/SETTER
 *    public int getAge() { return age; }
 *    public void setAge(int age) { ... validate ... this.age = age; }
 *
 * 3. VALIDATE IN SETTERS
 *    public void setEmail(String email) {
 *        if (isValidEmail(email)) {
 *            this.email = email;
 *        }
 *    }
 *
 * 4. USE MEANINGFUL NAMES
 *    Use camelCase: getName(), setAge(), calculateTotal()
 *
 * 5. HIDE INTERNAL COMPLEXITY
 *    Provide simple public interface
 *    Keep helper methods private
 *
 * 6. IMMUTABILITY (Optional)
 *    Setter not provided = Read-only field
 *    private final String id;
 *
 * COMMON MISTAKES IN ENCAPSULATION
 * ================================
 * 1. Making all variables public (defeats encapsulation)
 * 2. No validation in setters
 * 3. Exposing internal mutable objects directly
 * 4. Inconsistent getter/setter naming
 * 5. Not thinking about what should be hidden vs exposed
 *
 * ENCAPSULATION vs ABSTRACTION
 * ============================
 * ENCAPSULATION: HOW to hide data and implementation
 *   - Bundles data and methods together
 *   - Uses access modifiers (private, public, etc)
 *   - Focuses on "hiding" details
 *   - "Hiding is Good"
 *
 * ABSTRACTION: WHAT functionality to expose
 *   - Shows what a class does, not how it does it
 *   - Uses abstract classes and interfaces
 *   - Focuses on "simplifying" complex logic
 *   - "Simplifying is Good"
 *
 * EXAMPLE:
 * Car car = new Car();
 * car.accelerate();           <- ABSTRACTION (what it does)
 * // You don't know car uses fuel injection <- ENCAPSULATION (how it does)
 *
 */

// ============================================================================
// EXAMPLE 1: Without Encapsulation (BAD)
// ============================================================================

class BankAccountBad {
    public double balance;  // BAD: Public variable - anyone can modify
    public String accountNumber;

    // Problem: balance can be set to negative value
    // Problem: No validation
    // Problem: No audit trail
}

// Usage (Bad):
// BankAccountBad account = new BankAccountBad();
// account.balance = -5000;  // Invalid! But no one stops you.
// account.balance = -999999; // Disaster!

// ============================================================================
// EXAMPLE 2: With Encapsulation (GOOD)
// ============================================================================

class BankAccountGood {
    // Private variables - controlled access
    private double balance;
    private String accountNumber;
    private String accountHolder;

    // Constructor
    public BankAccountGood(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // GETTER: Read-only access to balance
    public double getBalance() {
        return balance;
    }

    // GETTER: Read-only access to account holder
    public String getAccountHolder() {
        return accountHolder;
    }

    // GETTER: Read-only access to account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // METHOD: Deposit money - with validation
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
    }

    // METHOD: Withdraw money - with validation
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive!");
            return;
        }
        if (amount > balance) {
            System.out.println("Error: Insufficient funds! Available: $" + balance);
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + ". New balance: $" + balance);
    }

    // METHOD: Transfer money to another account
    public void transferTo(BankAccountGood recipient, double amount) {
        if (recipient == null) {
            System.out.println("Error: Recipient account is null!");
            return;
        }
        if (amount > balance) {
            System.out.println("Error: Insufficient funds for transfer!");
            return;
        }
        this.withdraw(amount);
        recipient.deposit(amount);
        System.out.println("Transfer successful to " + recipient.accountHolder);
    }

    // Display account details
    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
}

// ============================================================================
// EXAMPLE 3: Employee Class with Encapsulation
// ============================================================================

class Employee {
    // Private variables
    private String employeeId;
    private String name;
    private int age;
    private double salary;
    private String email;

    // Constructor
    public Employee(String employeeId, String name, int age, String email, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }

    // GETTER: Get employee ID (immutable)
    public String getEmployeeId() {
        return employeeId;
    }

    // GETTER: Get name
    public String getName() {
        return name;
    }

    // SETTER: Set name with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }
        this.name = name;
    }

    // GETTER: Get age
    public int getAge() {
        return age;
    }

    // SETTER: Set age with validation
    public void setAge(int age) {
        if (age < 18 || age > 65) {
            System.out.println("Error: Age must be between 18 and 65!");
            return;
        }
        this.age = age;
    }

    // GETTER: Get salary
    public double getSalary() {
        return salary;
    }

    // SETTER: Set salary with validation
    public void setSalary(double salary) {
        if (salary < 0) {
            System.out.println("Error: Salary cannot be negative!");
            return;
        }
        this.salary = salary;
    }

    // GETTER: Get email
    public String getEmail() {
        return email;
    }

    // SETTER: Set email with validation
    public void setEmail(String email) {
        if (!email.contains("@")) {
            System.out.println("Error: Invalid email format!");
            return;
        }
        this.email = email;
    }

    // Display employee details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Salary: $" + salary);
    }
}

// ============================================================================
// MAIN METHOD: Testing Encapsulation
// ============================================================================

public class Encapsulation {
    public static void main(String[] args) {
        System.out.println("===== BANK ACCOUNT EXAMPLE =====");
        BankAccountGood account = new BankAccountGood("ACC123", "John Doe", 1000);
        account.displayDetails();

        System.out.println("\n--- Performing Operations ---");
        account.deposit(500);      // Valid deposit
        account.withdraw(200);     // Valid withdrawal
        account.withdraw(5000);    // Invalid - insufficient funds
        account.deposit(-100);     // Invalid - negative amount

        System.out.println("\n--- Transfer Example ---");
        BankAccountGood account2 = new BankAccountGood("ACC456", "Jane Smith", 500);
        account.transferTo(account2, 300);

        System.out.println("\n===== EMPLOYEE EXAMPLE =====");
        Employee employee = new Employee("E001", "Alice Johnson", 30, "alice@company.com", 50000);
        employee.displayDetails();

        System.out.println("\n--- Updating Employee Info ---");
        employee.setSalary(60000);     // Valid salary
        employee.setSalary(-5000);     // Invalid - negative
        employee.setAge(35);           // Valid age
        employee.setAge(75);           // Invalid - exceeds limit
        employee.setEmail("alice@newcompany.com");  // Valid email
        employee.setEmail("invalid-email");         // Invalid - no @

        System.out.println("\n--- Final Employee Details ---");
        employee.displayDetails();

        System.out.println("\n===== KEY TAKEAWAYS =====");
        System.out.println("1. Private variables prevent direct invalid modifications");
        System.out.println("2. Getters and setters provide controlled access");
        System.out.println("3. Validation ensures data integrity");
        System.out.println("4. Internal logic is hidden from users");
        System.out.println("5. Changes to implementation don't affect external code");
    }
}