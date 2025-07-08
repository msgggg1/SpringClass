<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="http://localhost/jspPro/images/SiSt.ico">
<title>2025. 7. 8. 오전 11:15:14</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }   
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">kEnik HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
        /dept/emp.jsp
  </xmp>
  
   <table id="tbl-emp">
      <caption></caption>
      <thead>
      <tr>
        <th>Dno</th>
        <th>Dname</th>
        <th></th> 
        <th>Eno</th>
        <th>Ename</th>
        <th>Job</th>
        <th>Hiredate</th>
        <th>Sal</th>
        <th>Gr</th>
      </tr>
    </thead>
      
      <tbody>
           <c:forEach items="${desList}" var="dto"> <!-- 부서정보 -->
              <c:forEach items="${ dto.empList }" var="edto" varStatus="status"><!-- 사원정보 -->
                <tr>
                    <c:if test="${ status.first }">
                       <td rowspan="${ dto.empList.size() }">${ dto.deptno }</td>                  
                       <td rowspan="${ dto.empList.size() }">${ dto.dname }</td>
                   </c:if>     
                       <td><input type="checkbox" value="${edto.empno }" name="empno"></td>   
                      <td>${ edto.empno }</td>
                     <td>${ edto.ename }</td>
                     <td>${ edto.job }</td>
                     <td><fmt:formatDate value="${ edto.hiredate }"/></td>
                     <td>${ edto.sal }</td>                            
                      <td>${edto.salgradeDTO.grade }</td>
                </tr>
                </c:forEach> 
           </c:forEach>
      </tbody>
       
      <tfoot>
      <tr>
        <td colspan="9">
          <button id="home" class="home">HOme</button>
        </td>
      </tr>
    </tfoot>
    </table>
</div>
</body>
</html> 