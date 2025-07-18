<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<h2>로그인</h2>
	<h3 class="hidden">방문페이지 로그</h3>
	<ul id="breadscrumb" class="block_hlist clear">
		<li>HOME</li>
		<li>회원가입</li>
		<li>로그인</li>
	</ul>
	<h3 class="hidden">회원가입 폼</h3>
	<div id="join-form" class="join-form margin-large">
		<c:if test="${param.error eq true }">
			<div>
				<strong style="color: red">아이디 또는 패스워드가 일치하지 않습니다. .</strong><br />
				<c:if test="${ SPRING_SECURITY_LAST_EXCEPTION  != null}">
              		Message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</c:if>
			</div>
		</c:if>

		<form action="/login" method="post">
			<!-- 스프링시큐리티 사용할때 /login으로 정해져 있음 -->
			<fieldset>
				<legend class="hidden">로그인 폼</legend>
				<h3>
					<img src="${pageContext.request.contextPath}/images/txtTitle.png" />
				</h3>
				<ul id="loginBox">
					<li><label for="id">아이디</label><input name="username"
						class="text" id="id" /></li>
					<li><label for="pwd">비밀번호</label><input type="password"
						name="password" class="text" id="pwd" /></li>
					<li><label for="remember-me"> Remember-Me </label><input type="checkbox"
                  		name="remember-me" class="text" id="remember-me" /></li>   	
				</ul>
				<p>
					<input type="submit" id="btnLogin" value="" />
				</p>
				<ul id="loginOption">
					<li><span>아이디 또는 비밀번호를 분실하셨나요?</span><a href="/Member/FindID"><img
							alt="ID/PWD 찾기"
							src="${pageContext.request.contextPath}/images/btnFind.png" /></a></li>
					<li><span>아이디가 없으신 분은 회원가입을 해주세요.</span><a
						href="/Member/Agree"><img alt="회원가입"
							src="${pageContext.request.contextPath}/images/btnJoin.png" /></a></li>
				</ul>
			</fieldset>

			<!-- POST 방식의 요청에는 반드시 아래 CSRF 토큰이 있어야 한다.  -->
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }">
		</form>
	</div>

</div>
