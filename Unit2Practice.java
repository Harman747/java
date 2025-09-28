import java.io.*;
import java.util.*;

// Part a: Autoboxing and Unboxing
class AutoboxingUnboxing {
    public static void sumIntegers() {
        Integer a = 10;  // Autoboxing
        Integer b = 20;
        int sum = a + b; // Unboxing
        System.out.println("Sum: " + sum);
    }
}

// Part b: Serialization and Deserialization of Student Object
class Student implements Serializable {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class StudentSerialization {
    public static void serializeStudent() {
        Student s = new Student(101, "Alice");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            out.writeObject(s);
            System.out.println("Student object serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeStudent() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student s2 = (Student) in.readObject();
            System.out.println("Student object deserialized");
            System.out.println("ID: " + s2.id + ", Name: " + s2.name);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Part c: Employee Management System Using File Handling
class Employee implements Serializable {
    int id;
    String name;

    Employee(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "Employee ID: " + id + ", Name: " + name;
    }
}

class EmployeeManagement {
    static final String FILE_NAME = "employees.dat";

    public static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        Employee emp = new Employee(id, name);

        try (ObjectOutputStream out = createOutputStream()) {
            out.writeObject(emp);
            System.out.println("Employee added.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle appending objects without corrupting stream header
    private static ObjectOutputStream createOutputStream() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists() && file.length() > 0) {
            return new AppendableObjectOutputStream(new FileOutputStream(FILE_NAME, true));
        } else {
            return new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        }
    }

    public static void displayEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("Employee List:");
            while(true) {
                Employee emp = (Employee) in.readObject();
                System.out.println(emp);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Helper for appending objects to existing file without corrupting stream
class AppendableObjectOutputStream extends ObjectOutputStream {
    AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}

public class Unit2Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Part a: Autoboxing and Unboxing");
        AutoboxingUnboxing.sumIntegers();

        System.out.println("\nPart b: Serialization and Deserialization of Student");
        StudentSerialization.serializeStudent();
        StudentSerialization.deserializeStudent();

        System.out.println("\nPart c: Employee Management System");
        int choice;
        do {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice){
                case 1: EmployeeManagement.addEmployee(sc); break;
                case 2: EmployeeManagement.displayEmployees(); break;
                case 3: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while(choice != 3);

        sc.close();
    }
}
