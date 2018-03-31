package jdbc.spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberupdateServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      Class.forName(this.getInitParameter("driver"));
      conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username")
                                       , this.getInitParameter("password"));
      stmt = conn.createStatement();
      rs = stmt.executeQuery("select mno,email,mname,cre_date from members where mno="+request.getParameter("no"));
      rs.next();
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>회원정보</title></head>");
      out.println("<body><h1>회원정보</h1>");
      out.println("<form action='update' method='post'>");
      out.println("번호: <input type='text' name='no' value='"+request.getParameter("no")+"'readonly><br>");
      out.println("이름: *<input type='text' name='name' value='"+request.getParameter("mname")+"'<br>");
      out.println("이메일: *<input type='text' name='email' value='"+request.getParameter("email")+"'<br>");
      out.println("가입일: "+rs.getDate("cre_date")+"<br>");
      out.println("<input type='submit' value='저장'");
      out.println("input type='button' value='취소'+ onclick='location.href=\"list\"'>");
      out.println("</form>");
      out.println("</body></html>");
      
    } catch(Exception e) {
      throw new ServletException(e);
    } finally {
      try {if (rs != null) rs.close();} catch(Exception e) {}
      try {if (stmt != null) stmt.close();} catch(Exception e) {}
      try {if (conn != null) conn.close();} catch(Exception e) {}
    }
  }
  
  
}
