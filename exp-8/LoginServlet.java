import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String u = req.getParameter("user");
    String p = req.getParameter("pass");
    if(u.equals("admin") && p.equals("123"))
      out.println("Welcome "+u);
    else
      out.println("Invalid Login");
  }
}
