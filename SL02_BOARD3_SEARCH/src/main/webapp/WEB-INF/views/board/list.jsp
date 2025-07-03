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
               <%-- <td><a href="/board/get?bno=${board.bno}"><c:out value="${board.title}" /></a></td> --%> 
               <td><a class="move" href="${board.bno}"><c:out value="${board.title}" /></a></td>
               <td><c:out value="${board.writer}" /></td>
               <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
               <td><fmt:formatDate value="${ board.updatedate }"  pattern="yyyy-MM-dd" /></td>
             </tr>
           </c:forEach>
         </c:otherwise>
       </c:choose>
       
       <!-- 검색 부분 -->
       <tr>
       	<td colspan="5" align="center">
       		<form id="searchForm" action="/board/list" method="get">
       			<select id="type" name="type">
       				<option value="T">제목</option>
       				<option value="C">내용</option>
       				<option value="W">작성자</option>
       				<option value="TC">제목+내용</option>
       				<option value="TW">제목+작성자</option>
       				<option value="TCW">제목+내용+작성자</option>
       			</select>
       			<input type="text" name="keyword" style="padding: 7px">
       			<button type="button" style="min-height: 32px">Search</button>
       			
       			<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
  				<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
       		</form>
       	</td>
       </tr>
       
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5">
          <div class="center">
            <div class="pagination">
              <!-- prev -->
              <c:if test="${pageMaker.prev}">
                <a href="${pageMaker.startPage-1}">&laquo;</a>
              </c:if>
              <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1" var="num">
                <%-- <a href="${pageContext.request.contextPath}/board/list?pageNum=${num}&amount=${pageMaker.criteria.amount}" class='${ num eq pageMaker.criteria.pageNum ? "active": "" }'>${num}</a> --%>
                <a href="${num}" class='${ num eq pageMaker.criteria.pageNum ? "active": "" }'>${num}</a>
              </c:forEach>
              <!-- next -->
              <c:if test="${pageMaker.next}">
                <a href="${pageMaker.endPage+1}">&raquo;</a>
              </c:if>
            </div><!-- pagination --> 
          </div><!-- center -->
        </td>
      </tr>
    </tfoot> 
  </table>
  
  <form id="actionForm" action="/board/list" method="get">
  	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
  	<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
  	<!-- 검색조건, 검색어 등등 -->
  	<input type="hidden" name="type" value="${pageMaker.criteria.type}">
  	<input type="hidden" name="keyword" value="${pageMaker.criteria.keyword}">
  </form>
  
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
				alert(`${param.bno} 번이 삭제되었습니다.`);
				return;
			}
		} // f checkModal
		
		// 페이징블럭에서 번호를 클릭하여 이동
		var actionForm = $("#actionForm");

		$(".pagination a").on("click", function(){
			event.preventDefault();
			let pageNum = $(this).attr("href"); // 1, 2, 3 페이지 번호
			// $(this).attr("href", "/board/list~~") 유지보수 어려움
			actionForm
					.find(":hidden[name=pageNum]").val(pageNum)
					.end() //없으면 위만 제출
				.submit();
			
		});// $(".pagination a").on("click", function(){
		
		// 게시글 제목 클릭 -> 페이지 이동 + pageNum, amount
		$("a.move").on("click", function(){
			event.preventDefault();
			let bno = $(this).attr("href"); 
			// $(this).attr("href", "/board/list~~") 유지보수 어려움
			actionForm
				.attr("action","/board/get")
				.append(`<input type="hidden" name="bno" value="\${bno}">`)
				.submit();
			}); // $("a.move").on("click", function(){
				
			
			// 검색 버튼 클릭 처리
			var searchForm = $("#searchForm");

			$("#searchForm button").on("click", function(){
				
				if (!searchForm.find("input[name=keyword]").val()) {
					alert("검색어(keyword)를 입력하세요");
					return;
				}// if
				
				event.preventDefault();
				searchForm.find("input[name=pageNum]").val(1);
				searchForm.submit();
			})// $("#searchForm button").on("click", function(){
				
		  	// 검색조건  상태관리 
		   $("#type").val('${ empty param.type ? "T" : param.type }');   
		   $("#searchForm [name=keyword]").val('<c:out value="${pageMaker.criteria.keyword}"/>');
		   
			/* var $input = $("input[name='keyword']");
			$input.val("${pageMaker.criteria.keyword}");
			
			$("#type").val("${pageMaker.criteria.type}"); */
			
		
	})//$(function(){
</script>
</body>
</html> 




