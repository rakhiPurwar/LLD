package AbstractFactory;// ============================
/* 1️⃣ ABSTRACT PRODUCTS

🟢 What Is Happening Here?

If factory = WindowsFactory:

createButton() → WindowsButton

createCheckbox() → WindowsCheckbox

If factory = MacFactory:

createButton() → MacButton

createCheckbox() → MacCheckbox

Entire UI family switches together.

🟢 Why This Is Abstract Factory?

Because:

We are creating a family of related objects

We don’t directly use new WindowsButton()

Client depends only on GUIFactory

🔥 Interview Explanation

If interviewer asks:

Why use Abstract Factory here?

Answer:

Because we need to create families of related UI components (Button, Checkbox) that must be consistent for a specific platform like Windows or Mac.

🔥 Easy Memory Trick

Factory Method → One product
Abstract Factory → Multiple related products

        OR

Factory Method = Order coffee
Abstract Factory = Order full combo meal
// ============================
*/
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// ============================
// 2️⃣ WINDOWS FAMILY
// ============================

class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Windows Checkbox");
    }
}

// ============================
// 3️⃣ MAC FAMILY
// ============================

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering Mac Checkbox");
    }
}

// ============================
// 4️⃣ ABSTRACT FACTORY
// ============================

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// ============================
// 5️⃣ CONCRETE FACTORIES
// ============================

class WindowsFactory implements GUIFactory {

    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {

    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// ============================
// 6️⃣ CLIENT CODE
// ============================

public class AbstractFactoryDemo {

    public static void main(String[] args) {

        GUIFactory factory;

        // Suppose OS detected as Windows
        String os = "WINDOWS";

        if (os.equalsIgnoreCase("WINDOWS")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.paint();
        checkbox.paint();
    }
}