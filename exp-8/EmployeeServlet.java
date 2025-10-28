import java.io.*; import java.sql.*;
import javax.servlet.*; import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test","root","pass");
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery("select * from employee");
      out.println("<table border=1><tr><th>ID</th><th>Name</th></tr>");
      while(rs.next()) {
        out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
      }
      out.println("</table>");
      c.close();
    } catch(Exception e) { out.println(e); }
  }
}
