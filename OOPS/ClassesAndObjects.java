/* Why Use Classes and Objects?
1. Reusability: Write a class once and create multiple objects with different data. ♻️

2. Modularity: Classes help organize code into logical sections, making it easier to debug and maintain. 🧩

3. Abstraction: Focus on the essential details of an entity without worrying about the internal workings. 🔍

4. Scalability: Adding new features is straightforward without affecting existing code. 📈
 */


class Car{
    String color;
    String model;
    int year;

    public Car(String color, String model, int year){
        this.color = color;
        this.model = model;
        this.year = year;
    }

}

public class ClassesAndObjects{
    public static void main(String[] args) {
        Car car1 = new Car("Red", "Toyota Camry", 2020);
        Car car2 = new Car("Blue", "Honda Accord", 2019);

        System.out.println("Car 1: " + car1.color + ", " + car1.model + ", " + car1.year);
        System.out.println("Car 2: " + car2.color + ", " + car2.model + ", " + car2.year);
    }
}