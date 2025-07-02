<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${user}
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
	<a href="/time">/time</a>
</h3>
<h3>
	<a href="/scott/dept">부서 정보 조회</a>
</h3>
<h3>
	<a href="/scott/empdept">테스트</a>
</h3>

</body>
</html>
