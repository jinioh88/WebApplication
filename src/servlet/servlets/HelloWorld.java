package servlet.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {
  ServletConfig config;

  @Override
  public void destroy() {
    System.out.println("destroy()");
  }

  // 서블릿 설정정보
  @Override
  public ServletConfig getServletConfig() {
    System.out.println("getServletConfig()");
    return this.config;
  }

  @Override
  public String getServletInfo() {
    System.out.println("getServletInfo()");
    return "version=1.0";
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init() 호출!");
    this.config = config;
    
  }

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    System.out.println("service()");
  }

}
