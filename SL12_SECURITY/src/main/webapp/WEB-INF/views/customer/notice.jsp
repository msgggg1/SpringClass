﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<h2>공지사항 - ${ message }</h2>
	<h3 class="hidden">방문페이지 로그</h3>
	<ul id="breadscrumb" class="block_hlist clear">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항목록</li>
	</ul>
	<h3 class="hidden">공지사항 목록</h3>
	<form id="content-searchform" class="article-search-form"
		action="notice.jsp" method="get">
		<fieldset>
			<legend class="hidden"> 목록 검색 폼 </legend>
			<input type="hidden" name="pg" value="" /> <label for="f"
				class="hidden">검색필드</label> <select name="f">
				<option value="TITLE">제목</option>
				<option value="CONTENT">내용</option>
			</select> <label class="hidden" for="q">검색어</label> <input type="text"
				name="q" value="" /> <input type="submit" value="검색" />
		</fieldset>
	</form>
	<table class="article-list margin-small">
		<caption class="hidden">공지사항</caption>
		<thead>
			<tr>
				<th class="seq">번호</th>
				<th class="title">제목</th>
				<th class="writer">작성자</th>
				<th class="regdate">작성일</th>
				<th class="hit">조회수</th>
			</tr>
		</thead>
		<tbody>
			<!-- 
							<tr>
								<td class="seq">1</td>
								<td class="title"><a href="noticeDetail.jsp">강좌가 오픈될 예정입니다.</a></td>
								<td class="writer">관리자</td>
								<td class="regdate">2013-02-10</td>
								<td class="hit">12</td>
							</tr>
 -->
			<c:choose>
				<c:when test="${ not empty list }">
					<c:forEach items="${ list }" var="dto">
						<tr>
							<td class="seq">${ dto.seq }</td>
							<td class="title"><a
								href="noticeDetail.htm?seq=${ dto.seq }">${ dto.title }</a></td>
							<td class="writer">${ dto.writer }</td>
							<td class="regdate">${ dto.regdate }</td>
							<td class="hit">${ dto.hit }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">공지사항이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p class="article-comment margin-small">
		<a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	</p>
	<p id="cur-page" class="margin-small">
		<span class="strong">1</span> / 10 page
	</p>
	<div id="pager-wrapper" class="margin-small">
		<div class="pager clear">
			<p id="btnPrev">
				<a class="button btn-prev" href="notice.jsp">이전</a>
			</p>
			<ul>
				<li><a class="strong" href="">1</a></li>
				<li><a href="">2</a></li>
				<li><a href="">3</a></li>
				<li><a href="">4</a></li>
				<li><a href="">5</a></li>
			</ul>
			<p id="btnNext">
				<span class="button btn-next">다음</span>
			</p>
		</div>
	</div>
</div>
<!-- tiles에서 조각모음 -->