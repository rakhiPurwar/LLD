/*
===============================
        INHERITANCE NOTES
===============================

Definition:
Inheritance is an OOP concept where one class (child/subclass)
acquires properties and behavior of another class (parent/superclass).

Keyword used in Java:
    extends  -> for classes
    implements -> for interfaces
*/


// =============================
// 1️⃣ SINGLE INHERITANCE
// =============================

class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}


// =============================
// 2️⃣ MULTILEVEL INHERITANCE [child class inherits from a child class]
// =============================

class Puppy extends Dog {
    void weep() {
        System.out.println("Puppy is weeping");
    }
}


// =============================
// 3️⃣ HIERARCHICAL INHERITANCE multiple child class inherits from same parent.
// =============================

class Cat extends Animal {
    void meow() {
        System.out.println("Cat is meowing");
    }
}


// =============================
// 4️⃣ MULTIPLE INHERITANCE one child class inheriting from multiple parent class
// (NOT supported with classes in Java)
// Supported using INTERFACES
// =============================

interface A {
    void show();
}

interface B {
    void display();
}

class C implements A, B {
    public void show() {
        System.out.println("Show method from Interface A");
    }

    public void display() {
        System.out.println("Display method from Interface B");
    }
}


// =============================
// 5️⃣ HYBRID INHERITANCE
// Combination of multiple types
// Supported using interfaces
// =============================


// =============================
// DIAMOND PROBLEM (Concept)
// =============================
/*
      A
     / \
    B   C
     \ /
      D

If B and C override same method from A,
which version should D inherit?

To avoid this ambiguity,
Java does NOT allow multiple inheritance with classes.
*/


// =============================
// MAIN METHOD TO TEST
// =============================

public class Inheritance {

    public static void main(String[] args) {

        // Single Inheritance
        Dog dog = new Dog();
        dog.eat();
        dog.bark();

        System.out.println("----------------");

        // Multilevel Inheritance
        Puppy puppy = new Puppy();
        puppy.eat();
        puppy.bark();
        puppy.weep();

        System.out.println("----------------");

        // Hierarchical Inheritance
        Cat cat = new Cat();
        cat.eat();
        cat.meow();

        System.out.println("----------------");

        // Multiple Inheritance using Interfaces
        C obj = new C();
        obj.show();
        obj.display();
    }
}