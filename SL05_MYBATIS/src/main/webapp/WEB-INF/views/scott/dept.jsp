<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="http://localhost/jspPro/images/SiSt.ico">
<title>2025. 6. 30. 오후 2:45:33</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<script src="/resources/js/dept.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }   
</style>
<style>
   /* The Modal (background) */
   .modal {
     display: none; /* Hidden by default */
     position: fixed; /* Stay in place */
     z-index: 1; /* Sit on top */
     padding-top: 100px; /* Location of the box */
     left: 0;
     top: 0;
     width: 100%; /* Full width */
     height: 100%; /* Full height */
     overflow: auto; /* Enable scroll if needed */
     background-color: rgb(0,0,0); /* Fallback color */
     background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
   }
   
   /* Modal Content */
   .modal-content {
     position: relative;
     background-color: #fefefe;
     margin: auto;
     padding: 0;
     border: 1px solid #888;
     width: 40%;
     box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
     -webkit-animation-name: animatetop;
     -webkit-animation-duration: 0.4s;
     animation-name: animatetop;
     animation-duration: 0.4s
   }
   
   /* Add Animation */
   @-webkit-keyframes animatetop {
     from {top:-300px; opacity:0} 
     to {top:0; opacity:1}
   }
   
   @keyframes animatetop {
     from {top:-300px; opacity:0}
     to {top:0; opacity:1}
   }
   
   /* The Close Button */
   .close {
     color: white;
     float: right;
     font-size: 28px;
     font-weight: bold;
   }
   
   .close:hover,
   .close:focus {
     color: #000;
     text-decoration: none;
     cursor: pointer;
   }
   
   .modal-header {
     padding: 2px 16px;
     background-color: white;
     color: black;
   }
   
   .modal-body {padding: 2px 16px;}
   
   .modal-footer {
     padding: 2px 16px;
     background-color: gray;
     color: white;
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
        WEB-INF/views/scott/dept.jsp
  </xmp>
  
  <form action="/scott/emp" method="post">
  <table id="tbl-dept">
  <caption></caption>
  <thead>
  	<tr>
  		<th></th>
  		<th>DeptNo</th>
        <th>DName</th>
        <th>Loc</th>
        <th>Edit</th>
  	</tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="dto">
  		<tr>
  			<td><input type="checkbox" name="deptno" value="${dto.deptno}" data-deptno="${dto.deptno}"></td>
  			<td>${dto.deptno}</td>
  			<td>${dto.dname}<span class="badge right red">${dto.numberOfEmps}</span></td>
  			<td>${dto.loc}</td>
  			<td align="center">
  				<span class="material-symbols-outlined delete" data-deptno="${dto.deptno}">close</span>
  			</td>
  		</tr>
  	</c:forEach>
  </tbody>
  <tfoot>
   <tr>
   	<td colspan="5">
   		<button id="search" class="search" type="button">search</button>
   		<button id="add" type="button" class="add">부서추가</button>
   	</td>
   </tr>
  </tfoot>
  </table>
  </form>
  
</div>

<!-- The Modal -->
<div id="add-modal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header"> 
      <h2>Ajax 부서 추가</h2>
    </div>
    <div class="modal-body">
      <div class="group">
        <label>부서번호</label>
        <input type="text" class="short" name="deptno" value="50">
       </div>
       <div class="group">
           <label>부서명</label>
           <input type="text" class="short" name="dname" value="QC">
       </div>
       <div class="group">
           <label>지역명</label>
           <input type="text" class="short" name="loc" value="SEOUL">
       </div>
       <div>
           <button id="add-dept" type="button" class="ok">확인</button>
           <button type="button" class="cancel">닫기</button>
       </div>
    </div>
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div> 
</div>  
  
<script>
	$(function(){
		
		//
		var addModal = $("#add-modal");
		
		// 부서 추가 버튼 클릭
		$("#add").on("click", function(){
			//addModal.css("display", "block");
			addModal.show();
			event.stopPropagation();
		});

		// 닫기 버튼 클릭
		$(".cancel").on("click", function(){
			addModal.css("display", "none");
			event.stopPropagation();
		});

		// 회색 영역 닫기
		/*
		$("body").on("click", function(){
			if (event.currentTarget != addModal ){ // HtmlBodyElement
				//addModal.css("display", "none")
				addModal.hide();
			}
		});
		*/
		
		// 모달창 "확인" 버튼 클릭
		$("#add-modal #add-dept").on("click", function(){
			let vdeptno = $("#add-modal :text[name=deptno]").val();			
			let vdname = $("#add-modal :text[name=dname]").val();			
			let vloc = $("#add-modal :text[name=loc]").val();				
			
			//js object
			const dept = {
				deptno : vdeptno,
				dname : vdname,
				loc : vloc
			};
			
			//dept.js add() ajax 정보 추가
			deptService.add(dept, function(result){
				// 1. 모달창 닫기
				addModal.hide();
				// 2. 응답 메시지 처리
				if (result == "SUCCESS") {
					 let tr = $(`
		                       <tr>
		                         <td><input type="checkbox" data-deptno="\${ vdeptno }" value="\${ vdeptno }" name="deptno"></td>
		                        <td>\${ vdeptno }</td>
		                        <td>\${ vdname }<span class="badge right red">0</span></td>
		                        <td>\${ vloc }</td>
		                        <td align="center"><span class="material-symbols-outlined delete" data-deptno="\${ vdeptno }">close</span></td>
		                      </tr>
		                       `);   
					 // append(), appendTo()
					 // 부모.append(자식)
					 // 자식.appendTo(부모) -- find method때문에
					$(tr)
                    .appendTo(  $("table tbody"))
                    .find("span.delete")
                        .on("click", function (){
                           // alert("클릭한 부서 삭제!!!");
                          if (   confirm("정말 삭제할까요?")  ) {
                             // data-deptno="50"
                               let deptno = $(this).data("deptno");
                              var spanDelete = $(this);
                              deptService.remove(deptno, function (result){
                             if(result === 'SUCCESS')
                               spanDelete.closest("tr").remove();
                              });
                          } // if
                        });
				} // if
				alert(result);
			});
		})
				
		// X 부서 삭제 span
		// #tbl-dept > tbody > tr:nth-child(3) > td:nth-child(5) > span
		$("#tbl-dept > tbody > tr > td:nth-child(5) > span.delete").on("click", function(){
			//alert("클릭한 부서 삭제")
			if (confirm("정말 삭제할까요?")) {
				//data-deptno=50;
				let deptno = $(this).data("deptno");
				deptService.remove(deptno, function(result){
					if (result == "SUCCESS") {
						$(event.currentTarget).closest("tr").remove();
					}// if
				});
			}
		})
		
		// 부서원 검색
		$("#search").on("click", function(){
			if(!$("tbody :checkbox:checked").length){
				alert("부서를 체크하세요");
				return;
			} // if
			
			// GET 방식 ?deptno=10&deptno=20
			$("form").submit();
		});
	}); // document.ready() 닫기
	
</script>
</body>
</html> 