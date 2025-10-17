import java.sql.*;
import java.util.Scanner;

public class MainApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (true) {
                System.out.println("1. Fetch Product Data\n2. Product CRUD\n3. Student CRUD\n0. Exit");
                int choice = sc.nextInt();
                if (choice == 0) break;
                switch (choice) {
                    case 1: fetchProductData(conn); break;
                    case 2: productCRUD(conn, sc); break;
                    case 3: studentCRUD(conn, sc); break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Part a: Fetch Product Data
    static void fetchProductData(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM product");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getDouble("price"));
        }
        rs.close(); st.close();
    }

    // Part b: CRUD Product Table
    static void productCRUD(Connection conn, Scanner sc) throws SQLException {
        System.out.println("a. Add\nb. Get\nc. Update\nd. Delete");
        String op = sc.next();
        switch (op) {
            case "a":
                System.out.print("Name: "); String name = sc.next();
                System.out.print("Price: "); double price = sc.nextDouble();
                PreparedStatement psAdd = conn.prepareStatement("INSERT INTO product (name, price) VALUES (?, ?)");
                psAdd.setString(1, name); psAdd.setDouble(2, price); psAdd.executeUpdate(); psAdd.close();
                break;
            case "b":
                System.out.print("ID: "); int id = sc.nextInt();
                PreparedStatement psGet = conn.prepareStatement("SELECT * FROM product WHERE id=?");
                psGet.setInt(1, id); ResultSet rs = psGet.executeQuery();
                if (rs.next()) System.out.println(rs.getInt("id") + " " + rs.getString("name"));
                rs.close(); psGet.close();
                break;
            case "c":
                System.out.print("ID: "); id = sc.nextInt();
                System.out.print("New Name: "); name = sc.next();
                System.out.print("New Price: "); price = sc.nextDouble();
                PreparedStatement psUpd = conn.prepareStatement("UPDATE product SET name=?, price=? WHERE id=?");
                psUpd.setString(1, name); psUpd.setDouble(2, price); psUpd.setInt(3, id); psUpd.executeUpdate(); psUpd.close();
                break;
            case "d":
                System.out.print("ID: "); id = sc.nextInt();
                PreparedStatement psDel = conn.prepareStatement("DELETE FROM product WHERE id=?");
                psDel.setInt(1, id); psDel.executeUpdate();
                psDel.close();
                break;
        }
    }

    // Part c: Student Management
    static void studentCRUD(Connection conn, Scanner sc) throws SQLException {
        System.out.println("a. Add\nb. Get\nc. Update\nd. Delete");
        String op = sc.next();
        switch (op) {
            case "a":
                System.out.print("Name: "); String name = sc.next();
                System.out.print("Age: "); int age = sc.nextInt();
                PreparedStatement psAdd = conn.prepareStatement("INSERT INTO student (name, age) VALUES (?, ?)");
                psAdd.setString(1, name); psAdd.setInt(2, age); psAdd.executeUpdate(); psAdd.close();
                break;
            case "b":
                System.out.print("ID: "); int id = sc.nextInt();
                PreparedStatement psGet = conn.prepareStatement("SELECT * FROM student WHERE id=?");
                psGet.setInt(1, id); ResultSet rs = psGet.executeQuery();
                if (rs.next()) System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age"));
                rs.close(); psGet.close();
                break;
            case "c":
                System.out.print("ID: "); id = sc.nextInt();
                System.out.print("New Name: "); name = sc.next();
                System.out.print("New Age: "); age = sc.nextInt();
                PreparedStatement psUpd = conn.prepareStatement("UPDATE student SET name=?, age=? WHERE id=?");
                psUpd.setString(1, name); psUpd.setInt(2, age); psUpd.setInt(3, id); psUpd.executeUpdate(); psUpd.close();
                break;
            case "d":
                System.out.print("ID: "); id = sc.nextInt();
                PreparedStatement psDel = conn.prepareStatement("DELETE FROM student WHERE id=?");
                psDel.setInt(1, id); psDel.executeUpdate();
                psDel.close();
                break;
        }
    }
}
