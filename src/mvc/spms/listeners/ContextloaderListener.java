package mvc.spms.listeners;

import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebListener;

import jdbc.spms.dao.MemberDao;
import mvc.spms.util.DBConnectionPool;

@WebListener
public class ContextloaderListener implements ServletContextListener {
  DBConnectionPool connPool;
  
  @Override
  public void contextInitialized(ServletContextEvent event) {
    try {
      ServletContext sc = event.getServletContext();
      connPool = new DBConnectionPool(
          sc.getInitParameter("driver"),
          sc.getInitParameter("url"),
          sc.getInitParameter("username"),
          sc.getInitParameter("password"));

      MemberDao memberDao = new MemberDao();
      memberDao.setDbConnetionPool(connPool);
      sc.setAttribute("memberDao", memberDao);

    } catch(Throwable e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    try {
      connPool.closeAll();
    } catch (Exception e) {}
  }
}
