<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<h3>
	사원번호 입력 : <input type="text" id="empno" name="empno">
	<button type="button" id="idCheckButton">중복확인</button>
	<div id="idCheckResult"></div>
</h3>


<script>
	$(function(){
		$("#idCheckButton").on("click", function(){
			
			const vempno = $("#empno").val();
			
			$.ajax({
			type : 'get',
			url : '/emp/check',
			data : {empno: vempno},
			contentType : "application/json; charset=utf-8",
			cache:false, 
			success: function (result, status, xhr ){
				console.log(result);
				console.log(typeof result);
				if (result == 1) {
		        	$("#idCheckResult").text("이미 사용 중인 아이디 입니다.")					
				} else if(result == 0){
		        	$("#idCheckResult").text("사용가능한 아이디 입니다.")					
				}
	       },
		      error: function (xhr, status, er ){}
		       
	    }); // ajax
			
		});
		
	})
</script>
</body>
</html>
