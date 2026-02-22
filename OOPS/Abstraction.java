/*
 * ABSTRACTION IN JAVA
 * ===================
 *
 * WHAT IS ABSTRACTION?
 * Hiding implementation details and showing only the essential features.
 * Real-world example: Car - you use steering wheel and pedals without knowing
 * how the engine, transmission, or electrical systems work internally.
 *
 * ABSTRACT CLASSES vs INTERFACES - THE DIFFERENCE
 * ================================================
 *
 * ABSTRACT CLASSES:
 * - Used when classes share COMMON CODE and STATE (variables)
 * - Can have instance variables and constructors with logic
 * - Methods can have implementation or be abstract
 * - Supports only SINGLE INHERITANCE (extends one class)
 * - Use for "IS-A" relationships (Dog IS-A Animal)
 * - Can have access modifiers: private, protected, public
 * Example: abstract class Animal { String name; }
 *
 * INTERFACES:
 * - Used to define a BEHAVIOR CONTRACT (what methods must exist)
 * - All methods are PUBLIC (interface methods are implicitly public)
 * - Can have default methods (Java 8+) with implementation
 * - Supports MULTIPLE IMPLEMENTATION (implement multiple interfaces)
 * - Use for "CAN-DO" capabilities (Dog CAN-DO Pet behavior)
 * - Better for defining capabilities across unrelated classes
 * Example: interface Pet { void play(); }
 *
 * PROBLEMS WITHOUT ABSTRACTION:
 * ============================
 * 1. CODE DUPLICATION: Dog, Cat, Bird all repeat sleep(), eat() logic
 * 2. MAINTENANCE NIGHTMARE: Bug fix? Update in every class
 * 3. LACK OF FLEXIBILITY: Can't treat different animals uniformly
 * 4. TIGHT COUPLING: Code depends on concrete classes, not abstractions
 * 5. POOR SCALABILITY: Adding new animals = rewriting everything
 * 6. VIOLATES DRY PRINCIPLE: Don't Repeat Yourself
 *
 * KEY CONCEPTS:
 * =============
 * - ABSTRACT METHODS: No body, must be implemented by subclass
 *   Example: abstract void sound();
 *
 * - DEFAULT METHODS (Java 8+): Have body, optional to override
 *   Example: default void commonMethod() { System.out.println("common"); }
 *
 * - ALL interface methods are PUBLIC (must declare @Override as public)
 *
 * - CONFLICT RESOLUTION: When multiple interfaces have same method,
 *   Use InterfaceName.super.methodName() to call specific interface
 *
 * WHEN TO USE WHAT - DECISION GUIDE:
 * ==================================
 * Use ABSTRACT CLASS if:
 * - Classes share common code AND state
 * - You need non-public methods (private, protected)
 * - Constructors need complex initialization
 * - IS-A relationship (Dog IS-A Animal)
 *
 * Use INTERFACE if:
 * - Defining behavior contract for unrelated classes
 * - CAN-DO relationship (Dog CAN-DO Pet, Duck CAN-DO Pet)
 * - You want multiple inheritance of type
 * - Classes don't share common state
 * - Want to define capability/role
 *
 * REAL-WORLD ANALOGY:
 * ==================
 * ABSTRACT CLASS = Blueprint (Vehicle blueprint has wheels, engine)
 * INTERFACE = Contract (Drivable contract - any vehicle can implement it)
 */

abstract class AnimalKingdom {
    String name;
    AnimalKingdom(String name) {
        this.name = name;
    }
    void commonMethod() {
        System.out.println("Animal common method.");
    }
    abstract void sound();
}

interface AnimalKingdoms{

    default void commonMethod() {
        System.out.println("Animal common method.");
    }
    void sound();
}

/*
 * DEFAULT METHODS (Java 8+)
 * =========================
 * Default methods have an implementation body in the interface.
 * They were introduced in Java 8 to provide backward compatibility.
 * This allows interfaces to evolve by adding new methods
 * without breaking existing implementations of the interface.
 *
 * ABSTRACT METHODS vs DEFAULT METHODS:
 * - Abstract method: No body, MUST be implemented by subclass
 * - Default method: Has body, CAN be optionally overridden
 */
interface Pet {
    void play();
    default void commonMethod() {
        System.out.println("Animal common method.");
    }
}

class DogKingdom extends AnimalKingdom implements Pet, AnimalKingdoms {
    DogKingdom(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " barks.");
    }

    @Override
    public void play() {
        System.out.println(name + " plays fetch.");
    }

    @Override
    public void commonMethod() {
        System.out.println(
                "Resolving conflict between Pet and AnimalKingdom");
        AnimalKingdoms.super.commonMethod();
        Pet.super.commonMethod();
        System.out.println(
                "Custom behavior: dog decides which common method to make.");
    }
}

/*
 * INTERFACE CONFLICT RESOLUTION
 * =============================
 * When a class implements multiple interfaces with the same method name,
 * you MUST override that method and explicitly choose which implementation to use.
 *
 * Usage: InterfaceName.super.methodName()
 *
 * In DogKingdom example:
 * - Both AnimalKingdoms and Pet have commonMethod()
 * - DogKingdom MUST override it
 * - Can call both: AnimalKingdoms.super.commonMethod() and Pet.super.commonMethod()
 */

//Abstract methods have no body and must be implemented by a class that implements the interface.
// Default methods have a body and can be optionally overridden by implementing classes.
//here Bird only implements abstractmethod not the default


class Bird implements Pet {

    @Override
    public void play() {
        System.out.println("Bird is flying.");
    }

    @Override
    public void commonMethod() {
        System.out.println("Bird's common method.");
    }
}


public class Abstraction {
    public static void main(String[] args) {
        DogKingdom dogKingdom = new DogKingdom("Buddy");
        dogKingdom.sound(); // Output: Buddy barks.
        dogKingdom.play();  // Output: Buddy plays fetch.
        dogKingdom.commonMethod(); // Shows conflict resolution

        System.out.println("\n--- Bird Example ---");
        Bird bird = new Bird();
        bird.play();  // Output: Bird is flying.
        bird.commonMethod(); // Output: Bird's common method.
    }
}