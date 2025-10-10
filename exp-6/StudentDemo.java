import java.io.*;

// Serializable Student class
class Student implements Serializable {
    int studentID;
    String name;
    double grade;

    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public String toString() {
        return "StudentID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

// Main class containing serialization and deserialization logic
public class StudentDemo {
    public static void main(String[] args) {
        Student student = new Student(101, "Alice", 88.5);

        // Serialize the Student object to "student.ser"
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            out.writeObject(student);
            System.out.println("Student object serialized.");
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }

        // Deserialize the Student object from "student.ser"
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserialized = (Student) in.readObject();
            System.out.println("Deserialized Student: " + deserialized);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
    }
}
