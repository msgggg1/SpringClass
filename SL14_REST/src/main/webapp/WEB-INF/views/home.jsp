<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


  <h3><a href="/time">/time</a></h3>
  <!-- 단순 문자열  -->
  <h3><a href="/sample/getText">/sample/getText</a> </h3>
	<!-- 자바 객체 -->
  <h3><a href="/sample/getSample">/sample/getSample</a> </h3>
	<!-- 자바 컬렉션 -->
  <h3><a href="/sample/getList">/sample/getList</a> </h3>
  <!-- 사원번호 7369인 사원 정보를 XML, JSON 응답 -->
  <h3><a href="/scott/employee/7369">/scott/employee/7369</a></h3>
	<!-- empno 중복체크 --> 
  <h3><a href="/scott/idCheck/7369">/scott/idCheck</a><br></h3>
  	<!-- 모든 사원정보 조회 --> 
  <h3><a href="/scott/emplist">/scott/emplist</a><br></h3>
  
  

</body>
</html>
