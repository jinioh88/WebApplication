#MVC 아키텍처
1. 컨트롤러(서블릿)
  - 클라이언트 요청 받았을 때 요청에 대해 해당 모델 컴포넌트를 호출.
  - 클라이언트가 보낸 데이터 가 있다면 전달하기 쉽게 데이터 가공.
  - 결과를 뷰에 전달에 화면생성하게. 
  
2. 모델
  - 데이터 저장소와 연동해 데이터를 다룬다. 
  
3. 뷰(JSP)
  - 모델이 처리한 작업 결과를 가지고 사용자에게 출력할 화면을 만듬. 

4. JSP 구동원리
  1) 서버에 JSP 파일을 저장. 클라이언트가 JSP 파일 실행 요청 --> 서블릿 컨테이너는 JSP파일에 대응하는 자바 서블릿을 찾아 실행.
  2) JSP 서블릿이 없거나  ,JSP 파일이 변경되면 JSP 엔진을 통해 서블릿 자바 소스를 생성
  3) 자바 컴파일러를 통해 서블릿 자바 소스는 서블릿 클래스 파일로 컴파일
  4) JSP로 부터 생성된 서블릿을 서블릿 구동 박식에 따라 실행.
  
5. 템플릿 데이터
  - 클라이언트로 출력되는 컨텐츠
  
6. 값 객체(VO) = 데이터 수송 객체(DTO)
  - 데이터 베이스에서 가져온 정보를 JSP 페이지에 전달하려면 정보를 담을 객체가 필요하다. 이를 값 객체 라고 부른다. 
  
7. RequestDispatcher
  - 얻을 떈 반드시 어떤 서블릿/JSP로 위임할 것인지 알려줘야함.
  - forawrd : 위임 후 해당 서블릿으로 돌아오지 않는다.
  - include : 서블릿으로 제어권 넘긴 후 그 서블릿 직업 끝나면 다시 제어권이 넘어온다. 
  
8. 데이터 보관소
  1) ServletContext 보관소
    - 웹 어플리케이션 시작 될 때 생성. 웹 어플리케이션 종료때까지 유지. 
    - 웹 애플리케이션 실행 되는 동안 모든 서블릿이 사용
    - sc.setAttribute("conn",conn);  conn정보를 모든 서블릿이 사용하게 저장.
    - DB 정보같이 다른 서블릿을 위해 준비 작업수행 서블릿은 <servlet-mapping> 태그가 없어 클라이언트에서 요청 불가
  2) HttpSession 보관소
    - 클라이언트 최초 요청 시 생성되 브라우저 닫을 때 까지 유지
    - 클라이언트 당 한개 생성됨. 
    - 웹 브라우저로 요청이 들어오면, 해당 웹브라우저를 위한 HttpSession 객체가 있는지 확인하고 없으면 새로 HttpSession 객체 만듬.
  3) ServletRequest 보관소
    - 클라이언트 요청이 들어올 때 생성. 클라이언트가 응답할 때까지 유지
    - 포워딩이나 인클루딩하는 서블릿들 사이에 값을 공유할 때 유용.
    - JSP 페이지 내부에서만 사용될 데이터를 공유할 때 사용.
  4) JspContext 보관소
    - JSP 페이지를 실행하는 동안 유지. 
    
9. EL 표기
  - JSP에서 주로 보관소에 들어있는 값을 꺼낼 떄 사용함. 
  - ${}(즉시적용), #{}(지연적용)사용해 값 표기. 
  - 보관소별 값 꺼낸는 것
    1) pageScope --> JspContext
    2) requestScope --> ServletRequest
    3) sessionScope --> HttpSession
    4) applicationScope --> ServletContext
         위 순서대로 객체 찾음

10. JSTL
  - JSTL 사용 시 JSP 페이지에서 자바코드 완전제거 가능해짐.
  - <c:out> : 출력문을 만듬
      <c:out value="${null}"</c:out>
  - <c:set> : 변수 생성하거나 기존 변수 값 덮어씀. 보관소에 저장됨. 
      <c:set var ="username" value="oh" scope="page|request|session|application"/>
  - <c:remove> : 보관소 값 제거 
      <c:remove var="username" scope="..."/> // scope 기본 값은 page
  - <c:if> : 
      <c:if test="${10>15}" var="result">
        10은 15보다 크다
      </c:if>
      ${result}
  - <c:choose> : switch, case 등과 같은 기능. <c:when>과 같이 사용. 
  - <c:forEach> : 반복적 작업시 사용. 
  - <c:forTokens> : 문자열을 특정 구분자로 분리해 반복문 만듬
  - <c:url> : url 만들때 사용. 
  
11. ServletContextListner와 객체 공유
  - 웹 어플리케이션의 시작과 종료 이벤트를 처리할 리스너.
  - 리스너란?
   - 서블릿 컨테이너는 사건이 발생 했을때 알림 기능을 제공한다. 
   - 규칙에 따라 객체를 만들어 DD 파일에 등록하면 된다. (@WebListener로 등록할 수도 있다)
   - 사건 발생 시 알림 받는 객체를 리스너 라고 한다. 
  - DAO 처럼 여러 서블릿이 공유하는 것이 메모리 관리, 성능면에서 좋다.
  - DAO 공유하려면 ServletContext에 저장하는 것이 좋다. 