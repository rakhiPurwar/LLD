/*

S — Single Responsibility Principle (SRP)
Meaning

A class should do only one job.
If a class has multiple responsibilities, it becomes hard to maintain.
So it has 3 reasons to change:

If tax rules change → change calculateTotal()

If DB changes → change saveToDatabase()

If email format changes → change sendEmail()

This creates:

messy code

hard testing (need DB/email to test invoice)

higher chance of breaking unrelated features
Invoice does:

calculates totals
saves to DB
sends email

So it has 3 reasons to change:

If tax rules change → change calculateTotal()

If DB changes → change saveToDatabase()

If email format changes → change sendEmail()

This creates:

messy code

hard testing (need DB/email to test invoice)

higher chance of breaking unrelated features

class Invoice {

    void calculateTotal() {
        System.out.println("Calculating total...");
    }

    void saveToDatabase() {
        System.out.println("Saving to database...");
    }

    void sendEmail() {
        System.out.println("Sending email...");
    }
}

public class SRPWrong {
    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.calculateTotal();
        invoice.saveToDatabase();
        invoice.sendEmail();
    }
}
❌ One class doing 3 jobs.
 */

class Invoice {
    void calculateTotal() {
        System.out.println("Calculating total...");
    }
}

class InvoiceRepository {
    void save(Invoice invoice) {
        System.out.println("Saving to database...");
    }
}

class EmailService {
    void send(Invoice invoice) {
        System.out.println("Sending email...");
    }
}

public class SRP {
    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.calculateTotal();

        new InvoiceRepository().save(invoice);
        new EmailService().send(invoice);
    }
}