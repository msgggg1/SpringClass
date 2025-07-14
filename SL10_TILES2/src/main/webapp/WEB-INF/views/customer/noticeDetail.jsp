<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li id="home"><a href="">HOME</a></li>
		<li><a href="">고객센터</a></li>
		<li><a href="">공지사항</a></li>
	</ul>
	<div id="notice-article-detail" class="article-detail margin-large">
		<dl class="article-detail-row">
			<dt class="article-detail-title">제목</dt>
			<dd class="article-detail-data">${ notice.title }</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">작성일</dt>
			<dd class="article-detail-data">${ notice.regdate }</dd>
		</dl>
		<dl class="article-detail-row half-row">
			<dt class="article-detail-title">작성자</dt>
			<dd class="article-detail-data half-data">${ notice.writer }</dd>
		</dl>
		<dl class="article-detail-row half-row">
			<dt class="article-detail-title">조회수</dt>
			<dd class="article-detail-data half-data">${ notice.hit }</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">첨부파일</dt>
			<dd class="article-detail-data">
				<%-- <a href="/customer/upload/${notice.filesrc}">${notice.filesrc }</a> --%>
				<a href="download.htm?dir=/customer/upload&file=${notice.filesrc}">${notice.filesrc}</a>
			</dd>
		</dl>

		<div class="article-content">${ notice.content }</div>
	</div>
	<p class="article-comment margin-small">
		<a class="btn-list button" href="notice.htm">목록</a> <a
			class="btn-edit button" href="noticeEdit.htm?seq=${notice.seq}">수정</a>
		<a class="btn-del button"
			href="noticeDel.htm?seq=${notice.seq}&filesrc=${notice.filesrc}">삭제</a>
		<%-- 	<form action="noticeDel.htm?seq=${notice.seq}" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
							<button type="submit" class="btn-del button" >삭제</button>
						</form> --%>
	</p>
	<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
		<dl class="article-detail-row">
			<dt class="article-detail-title">▲ 다음글</dt>
			<dd class="article-detail-data">다음 글이 없습니다.</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">▼ 이전글</dt>
			<dd class="article-detail-data">제 12회 창업스쿨</dd>
		</dl>
	</div>
</div>

<!-- 스크립트 코딩 살리기 -->
<script>
	$(function() {
		$("a.btn-del.button").on("click", function() {
			event.preventDefault();
			if (confirm("정말 삭제하시겠습니까?")) {
				location.href = $(this).attr("href");
			} // if
		});
	}); // $(function(){
</script>
