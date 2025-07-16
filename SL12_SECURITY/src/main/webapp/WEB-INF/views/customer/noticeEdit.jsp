<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>
	<form action="" method="post" enctype="multipart/form-data">
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" value='${notice.title}' />
				</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">작성자</dt>
				<dd class="article-detail-data half-data">${notice.writer}</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">${notice.hit}</dt>
				<dd class="article-detail-data half-data">1235</dd>
			</dl>
			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					&nbsp;<input type="file" id="txtFile" name="file" /> <input
						type="hidden" name="o_filesrc" value="${notice.filesrc}" />
					${notice.filesrc}
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${notice.content}
							</textarea>
				<img
					src="http://sstatic.naver.net/keypage/outside/info/2011031017145546407.jpg" /><br />
			</div>
		</div>
		<p class="article-comment margin-small">
			<!-- <form action="noticeEdit.htm" method="post">
						<button class="btn-save button">수정</button>
					</form> -->
			<input type="submit" class="btn-save button" value="수정" />
			<!-- <a class="btn-save button" href="noticeEdit.htm">수정</a> -->
			<a class="btn-cancel button" href="noticeDetail.htm?seq=${notice.seq}">취소</a>
		</p>
	</form>
</div>
