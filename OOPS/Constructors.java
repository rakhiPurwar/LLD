/*
1. A class can have multiple constructors through overloading, but they must differ in parameter lists.
2. Constructors can call other constructors in the same class using this().
3. Constructors can call parent class constructors using super() in java.
4. Always use constructors to ensure objects are in a consistent and valid state.
5. Utilize copy constructors carefully to avoid shallow copying when deep copying is required.
6. Leverage private constructors for Singleton patterns or utility classes.

 */
class Animal{
    String name;
    int age;

    // constructor
    public Animal(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void display(){
        System.out.println("Name: "+name+" Age: "+age);
    }
}

class Dog extends Animal {
    String breed;

    // constructor
    public Dog(String name, int age, String breed){
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Breed"+ breed);
    }
}// calling parent class constructor

public class Constructors {
    public static void main(String args[]){
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        dog.display();
    }
}

/*

1. Can a constructor be final, static, or abstract? Why or why not?

Answer: No, constructors cannot be final, static, or abstract because:

        • final: A constructor cannot be inherited, so final is irrelevant.

        • static: Constructors belong to objects, not the class itself.

        • abstract: A constructor must be concrete as it initializes an object.

2. What happens if you explicitly define a constructor with arguments but no default constructor?

Answer: The default constructor is not automatically provided. Attempting to create an object with no arguments will result in a compilation error.

3. What happens if a constructor is synchronized?

Answer: A synchronized constructor is allowed but makes no sense, as object-level synchronization is not applicable before the object is fully created.


4. Can a constructor have a return statement?

Answer: No, constructors cannot return a value, but they can have a return statement to exit early (without a value).

 */

