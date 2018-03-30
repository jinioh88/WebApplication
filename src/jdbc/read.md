#서블릿과 JDBC
서블릿 <--> JDBC Driver <--> 데이터베이스

1. Type-4 드라이버
  - MySql 통신 프로토콜에 맞춰 데이터베이스와 직젖ㅂ 통신해 ODBC 드라이버 필요 안한다. 
  
2. java.sql.Connection 객체
  - 연결에 성공하면 DB 젖ㅂ속 정보를 다루는 객체 반환. 
  - 반환된 객체로 sql문을 보내는 객체를 얻을 수 있다. (ex) conn.createStatement();

3. java.sql.Statement
  - 이 객체로 데이터베이스에 SQL문 보낼 수 있다. 
  - executeQuery() : select문
  - executeUpdate() : insert, update, delete, create, alter, drop
  - executeBatch() : addBatch()로 등록한 여러개 sql 문 한꺼번에 실행.
  
4. ResultSet 
  - 객체를 통해 행을 가져온다. 
  - 서버에서 받은 레코드는 ResultSet 객체에 보관된다.
  
5. 생성한 자원은 반드시 해제 하자 finally에서

6.HttpServlet
  - 다른 서블릿과 다르게 service()대신 doGet(), doPost()를 재정의한다. 