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

6. HttpServlet
  - 다른 서블릿과 다르게 service()대신 doGet(), doPost()를 재정의한다. 
  - 클라이언트 요청 들어면 --> HttpServlet의 service() 호출 됨. 
  - service()는 클라이언트 요청 방식에 따라 doGet(), doPost(), doPut() 드으이 메서드 호출
  - HttpServlet 상속 시 service() 직접구현 보단, doXXX를 오버라이딩 한다. 
  - doPost() : 
  
7. <form> 태그
  - action : 실행할 서블릿의 URL주소.
  - method : 서버에 요청하는 방식. 기본은 get.
  
8. 클라이언트로 부터 톰켓이 /member/add 요청 받으면, MemberAddServlet의 service()메서드 호출
   service()에서는 요청방식에 따라 doGet(), doPost() 호출해줌 .
   
9.
  1) PreparedStatement
    - 반복적 질의하거나, 입력 매개변수가 많은 경우 유용. 
    - 이미지 같은 바이너리 데이터 저장하거나 변성시 PreparedStatement만 가능하다.
    - SQL 문과 입력 매개변수가 분리되어 코드 작성이 편함
  2) Statement
    - 질의한 때마다 SQL문 컴파일
    - SQL문 안에 입력 매개변수 값 포함.    
  
10. JDBC API는 애플리케이션에서 DB 서버에 SQL 명령문을 보내 그결과를 받는다. 

11. 한글깨짐
  - 서블릿이 웹브라우저로 받은 한글 데이터는 UTF-8로 인코딩된 값이다.
  - 서블릿이 이 3바이트를 하나의 문자가 아닌 각각의 바이트를 개별 문자로 취급해 유니코드로 변환. 
  - getParameter() 호출전에 클라이언트가 보낸 매개변수의 값의 인코딩을 지정해야 한다. 
  
12. 리프래시
  - 일정시간 지나고 나서 자동으로 서버에 요청 보내는 것.
  - servlet에서 Header에 보내는 방법과, HTML <head> 태그에 meta 태그추가해 하는 방법이 있다. 
  - 작업 결과 출력 후 다른 페이지 이동 원하면 리스페시를 사용한다.
  - 작업결과 없이 이동은 리다이렉트로 처리한다. 
  
13. 리다이렉트
  - sendRedirect()
  - 응답코드가 302