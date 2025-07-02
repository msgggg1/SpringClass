<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3><a href="/time">/time</a></h3>

<hr>
<form>
	<label for="empno">아이디 :</label>
	<input type="text" id="empno" name="empno" autofocus="autofocus" value="7369">
	<button type="button" id="btnCheck">중복체크</button>
	<button type="button" id="btnCheck2">중복체크2</button>
	<br>
	<span id="idCheckResult"></span>
</form>

<script>
	$(function (){
		
		//[2]
		$("#btnCheck2").on("click", function(){
			//alert("확인");
			var empno = $("#empno").val()
			if(!empno){
				alert("아이디(empno)를 입력하세요");
				return;
			}
			
			// ajax 요청
			$.ajax({
				url: `${pageContext.request.contextPath}/empnoCheck/\${empno}`,
				type: "GET",
				success: function(response){
					if (response === "available") {
                        $("#idCheckResult").text("사용 가능한 아이디입니다.").css("color", "green");
                    } else {
                        $("#idCheckResult").text("이미 사용 중인 아이디입니다.").css("color", "red");
                    }
				},
				error: function(){
					
				}
			});
			
		})
		
		//[1]
		$("#btnCheck").on("click", function(){
			//alert("확인");
			var empno = $("#empno").val()
			if(!empno){
				alert("아이디(empno)를 입력하세요");
				return;
			}
			
			// ajax 요청
			$.ajax({
				url: "${pageContext.request.contextPath}/emp/empno",
				type: "GET",
				data: {empno: empno},
				success: function(response){
					if (response === "available") {
                        $("#idCheckResult").text("사용 가능한 아이디입니다.").css("color", "green");
                    } else {
                        $("#idCheckResult").text("이미 사용 중인 아이디입니다.").css("color", "red");
                    }
				},
				error: function(){
					
				}
			});
			
		})
	}); //$(function
</script>
</body>
</html>
