<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="csrf-token" content="${_csrf.token}">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/cdn-main/example.css">
<script src="resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }  
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">KenIk HOme</a></h1>
  <ul>  
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
  </xmp> 
  
  <h3> model - currentTime : ${ currentTime }</h3> 
  
  <input type="button" value="ajax request" id="test">
  <br>
  <input type="button" value="emp delete ajax request" id="emp_delete">
  <br>
  <p id="demo">
  </p>
</div> 

  <script>
    $("#test").on("click", function (event){
       let deptno = 10;
       
       $.ajax({
         url: `/sample/employees/\${deptno}.ajax`,
         method:"GET",
         dataType : "json",
         cache:false,
         data:{
               deptno2:30
               /* , '${_csrf.parameterName }' : '${_csrf.token }' */
            },
         success: function (result) {
            alert(result);
            // $("#demo").html(result[0].ename);
         }, error : function (xhr, data, textStatus) {
            if (xhr.status == 401) {
                      alert("로그인 X") 
                  }else{
                       alert("서버 에러") 
                  }
           } // success , error
      }); // ajax
    });   
      
    $("#emp_delete").on("click", function (event){
//       let empno = 7521;
       let empno = 7566;
       
       $.ajax({
         url: `/sample/employees/\${empno}.ajax`,
         method:"DELETE", 
         headers: {
              'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')  // CSRF 토큰 포함
          },
         cache:false,
         data:{
               deptno:30
         },
         success: function (result) {
            alert(result); 
         }, 
         error : function (xhr, data, textStatus) {
            if (xhr.status == 401) {
                      alert("로그인 X") 
                  }else{
                       alert("서버 에러") 
                  } // if
           } //  error
      }); // ajax 
    });
  </script>
</body>
</html>







 

