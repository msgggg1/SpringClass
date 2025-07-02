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
<title>2025. 7. 1. 오후 4:18:08</title>
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
        /board/list.jsp
  </xmp>
  
  <table>
    <caption style="text-align: right;">
      <a href="/board/register">글쓰기</a>
    </caption>
    <thead>
       <tr>
         <th>#번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>수정일</th>        
       </tr>
    </thead>
    <tbody>
       <c:choose>
         <c:when test="${ empty list }">
           <tr>
             <td colspan="5">no board...</td>
           </tr>
         </c:when>
         <c:otherwise>
           <c:forEach items="${ list }" var="board">
             <tr>
               <td><c:out value="${board.bno}" ></c:out></td>
               <%-- <td><a href="/board/get/${board.bno}"><c:out value="${board.title}" /></a></td>  --%>
               <td><a href="/board/get?bno=${board.bno}"><c:out value="${board.title}" /></a></td> 
               <td><c:out value="${board.writer}" /></td>
               <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
               <td><fmt:formatDate value="${ board.updatedate }"  pattern="yyyy-MM-dd" /></td>
             </tr>
           </c:forEach>
         </c:otherwise>
       </c:choose>
    </tbody>
  </table>
  
</div>

<script>
	$(function(){
		// rttr.addAttribute("result", 4); 쿼리스트링 경로?result=4
		var result = '<c:out value="${result}" />'; //JSTL 서버에서 먼저 실행
		//alert(result + "번 등록되었습니다.");
		checkModal(result);
		
		function checkModal(result){
			if (result === "" || history.state) return ;
			if(parseInt(result) > 0) { 
				alert(`\${result} 번 등록되었습니다.`);
				return;
			}else if(result === "REMOVESUCCESS"){
				alert(`${boardVO.bno} 번이 삭제되었습니다.`);
				return;
			}
		}
	})//$(function(){
</script>
</body>
</html> 




