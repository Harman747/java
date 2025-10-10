import java.util.*;
import java.util.stream.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary){
        this.id = id; this.name = name; this.salary = salary;
    }

    public String toString() {
        return id + " " + name + " " + salary;
    }
}

class Student {
    String name;
    double grade;

    Student(String name, double grade){
        this.name = name; this.grade = grade;
    }

    public String toString() {
        return name + " " + grade;
    }

    public double getGrade() { return grade; }
    public String getName() { return name; }
}

class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category){
        this.name = name; this.price = price; this.category = category;
    }

    public String toString() {
        return name + " " + price + " " + category;
    }
}

public class StreamOperationsDemo {
    public static void main(String[] args) {
        
        // Part a: Sorting Employees by salary with lambda
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 60000));
        employees.add(new Employee(2, "Bob", 55000));
        employees.add(new Employee(3, "John", 50000));
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));
        System.out.println("Employees sorted by salary:");
        employees.forEach(System.out::println);
        
        System.out.println();

        // Part b: Filtering and sorting Students by grade desc, name asc
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 75),
            new Student("Carol", 85),
            new Student("David", 90)
        );
        List<Student> filteredSortedStudents = students.stream()
            .filter(s -> s.getGrade() > 80)
            .sorted(Comparator.comparingDouble(Student::getGrade).reversed()
                    .thenComparing(Student::getName))
            .collect(Collectors.toList());
        System.out.println("Filtered & Sorted Students:");
        filteredSortedStudents.forEach(System.out::println);

        System.out.println();

        // Part c: Stream operation on Product dataset
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1000, "Electronics"),
            new Product("Shirt", 40, "Clothing"),
            new Product("Smartphone", 700, "Electronics"),
            new Product("Blender", 80, "Home Appliances")
        );
        List<Product> filteredProducts = products.stream()
            .filter(p -> p.category.equals("Electronics") && p.price > 500)
            .sorted(Comparator.comparingDouble(p -> p.price))
            .collect(Collectors.toList());
        System.out.println("Filtered Products:");
        filteredProducts.forEach(System.out::println);
    }
}
