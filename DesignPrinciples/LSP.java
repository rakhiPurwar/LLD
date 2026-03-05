/*
L — Liskov Substitution Principle
📌 Definition (Simple)

If B is a subclass of A, then B should be usable wherever A is expected — without breaking behavior.

In simple words:

👉 Child class must behave like the parent class promises.
👉 No surprises. No runtime errors.

We assume all vehicles have engines.

❌ Code (Copy-Paste Ready)
class Vehicle {
    void startEngine() {
        System.out.println("Engine started");
    }
}

class Car extends Vehicle {
    // Works fine
}

class Bicycle extends Vehicle {
    @Override
    void startEngine() {
        throw new UnsupportedOperationException("Bicycle has no engine!");
    }
}

public class LSPWrong {
    public static void main(String[] args) {
        Vehicle vehicle = new Bicycle();
        vehicle.startEngine(); // 💥 Runtime crash
    }
}

We said:

A Bicycle IS-A Vehicle

But:

Parent promises startEngine()

Bicycle cannot fulfill that promise

So it throws exception

That breaks substitutability

Code that works for Vehicle will fail for Bicycle.


Don’t force behaviors that don’t apply to all subclasses.

LSP ensures subclasses can replace parent classes without altering the correctness of the program.
 */

class Vehicle {
    void move() {
        System.out.println("Vehicle moving");
    }
}

class EngineVehicle extends Vehicle {
    void startEngine() {
        System.out.println("Engine started");
    }
}

class Truck extends EngineVehicle {
    // Car can start engine
}

class Bicycle extends Vehicle {
    // Bicycle only moves
}

public class LSP {
    public static void main(String[] args) {

        Vehicle bike = new Bicycle();
        bike.move(); // Works fine

        EngineVehicle truck = new Truck();
        truck.startEngine(); // Works fine
    }
}