import java.io.*;
import javax.servlet.*; import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String name = req.getParameter("name");
    String status = req.getParameter("status");
    out.println("Attendance saved for "+name+": "+status);
  }
}
