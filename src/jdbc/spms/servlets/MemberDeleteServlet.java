package jdbc.spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    Connection conn = null;
    Statement stmt = null;
    //ResultSet rs = null;
    
    try {
      ServletContext sc = this.getServletContext();
      conn = (Connection)sc.getAttribute("conn");
      stmt = conn.createStatement();
      stmt.executeUpdate("delete from members where mno="+request.getParameter("no"));
      
      response.sendRedirect("list");
    }catch(Exception e) {throw new ServletException(e);}
  }
  
}