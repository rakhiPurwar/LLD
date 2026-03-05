/*
Inheritance models “is-a” relationships,
composition and aggregation model “has-a” relationships (strong vs weak),
association represents general linking,
 and dependency represents temporary usage.
 Association is a relationship between two classes where objects of one class use or interact with
 objects of another class, without ownership or lifecycle dependency.
 */

// ----------------------
// 1️⃣ Inheritance (IS-A)
// ----------------------
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}

class Student extends Person {   // Student IS-A Person
    int rollNumber;

    Student(String name, int rollNumber) {
        super(name);
        this.rollNumber = rollNumber;
    }
}


// ----------------------
// 2️⃣ Aggregation (HAS-A weak)
// ----------------------
class Library {
    String libraryName;

    Library(String libraryName) {
        this.libraryName = libraryName;
    }
}

class University {
    Library library;   // Aggregation

    University(Library library) {
        this.library = library;
    }
}


// ----------------------
// 3️⃣ Composition (HAS-A strong)
// ----------------------
class Classroom {
    String roomNumber;

    Classroom(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}

class Department {
    private Classroom classroom;   // Composition

    Department(String roomNumber) {
        this.classroom = new Classroom(roomNumber);
    }

    void showClassroom() {
        System.out.println("Department classroom: " + classroom.roomNumber);
    }
}


// ----------------------
// 4️⃣ Association
// ----------------------
class Professor {
    String subject;

    Professor(String subject) {
        this.subject = subject;
    }
}

class Course {
    Professor professor;   // Association

    Course(Professor professor) {
        this.professor = professor;
    }

    void showCourse() {
        System.out.println("Course taught by professor of " + professor.subject);
    }
}


// ----------------------
// 5️⃣ Dependency
// ----------------------
class ReportGenerator {
    void generateReport(Student student) {
        System.out.println("Generating report for " + student.name);
    }
}


// ----------------------
// MAIN CLASS
// ----------------------
public class ClassRelationships {
    public static void main(String[] args) {

        // Inheritance
        Student student = new Student("Rakhi", 101);

        // Aggregation
        Library library = new Library("Central Library");
        University university = new University(library);

        // Composition
        Department department = new Department("Room A1");

        // Association
        Professor professor = new Professor("Computer Science");
        Course course = new Course(professor);

        // Dependency
        FactoryMethod.ReportGenerator reportGenerator = new FactoryMethod.ReportGenerator();
        reportGenerator.generateReport(student);

        // Display
        department.showClassroom();
        course.showCourse();
        System.out.println("University library: " + university.library.libraryName);
    }
}

