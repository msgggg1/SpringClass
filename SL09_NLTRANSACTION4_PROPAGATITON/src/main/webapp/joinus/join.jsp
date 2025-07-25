<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<link href="join.css" type="text/css" rel="stylesheet" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body>
		<div id="header">
			<div class="top-wrapper">
				<h1 id="logo"><a href="../index.jsp"><img src="../images/logo.png" alt="뉴렉처" /></a></h1>
				<h2 class="hidden">메인메뉴</h2>
				<ul id="mainmenu" class="block_hlist">
					<li>
						<a href="">학습가이드</a>
					</li>
					<li>
						<a href="" >과정선택</a>
					</li>
					<li>
						<a href="" >인기과정</a>
					</li>
				</ul>
				<form id="searchform" action="" method="get">
					<fieldset>
						<legend class="hidden">
							과정검색폼
						</legend>
						<label for="query">과정검색</label>
						<input type="text" name="query" />
						<input type="submit" class="button" value="검색" />
					</fieldset>
				</form>
				<h3 class="hidden">로그인메뉴</h3>
				<ul id="loginmenu" class="block_hlist">
					<li>
						<a href="../index.htm">HOME</a>
					</li>
					<li>
						<a href="../joinus/login.htm">로그인</a>
					</li>
					<li>
						<a href="../joinus/join.htm">회원가입</a>
					</li>
				</ul>
				<h3 class="hidden">회원메뉴</h3>
				<ul id="membermenu" class="clear">
					<li>
						<a href=""><img src="../images/menuMyPage.png" alt="마이페이지" /></a>
					</li>
					<li>
						<a href="../customer/notice.jsp"><img src="../images/menuCustomer.png" alt="고객센터" /></a>
					</li>
				</ul>
			</div>
		</div>
		<div id="visual" class="joinus">
			<div class="top-wrapper">

			</div>
		</div>
		<div id="main">
			<div class="top-wrapper clear">
				<div id="content">
					<form action="/joinus/join.htm" method="post">
						<h2>회원가입</h2>
						<h3 class="hidden">방문페이지 로그</h3>
						<p id="breadscrumb" class="block_hlist clear"><img alt="Step1 개인정보 등록" src="images/step2.png" /></p>
						<h3 class="hidden">회원가입 폼</h3>
						<div id="join-form" class="join-form margin-large" >						
							<dl class="join-form-row">
								<dt class="join-form-title">
									아이디
								</dt>
								<dd class="join-form-data">
									<input type="text" name="id" value="hgd"/>
									<input id="btnCheckUid" class="button" type="button" value="중복확인" />
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									비밀번호
								</dt>
								<dd class="join-form-data">
									<input type="password" name="pwd" value="1234" />
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									비밀번호 확인
								</dt>
								<dd class="join-form-data" >
									<input type="password" name="pwd2" value="1234" />
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									이름
								</dt>
								<dd class="join-form-data">
									<input type="text" name="name" value="홍길동" />
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									성별
								</dt>
								<dd class="join-form-data">
									<select name="gender">
										<option selected="selected">남성</option>
										<option>여성</option>
									</select>
								</dd>
							</dl>
							<dl class="join-form-row birthday">
								<dt class="join-form-title">
									생년월일
								</dt>
								<dd class="join-form-data">								
	                                <span>
	                                    <input type="text" name="year"  id="year"  value="2001"/>년
	                                    <input type="text" name="month" id="month" value="7"/>월
	                                    <input type="text" name="day" id="day" value="1" />일
	                                    <input type="hidden" name="birth" id="birth" />
	                                </span>
	                                    <script>
                                         // focus <-> blur 이벤트 언제 발생...
                                         /* [1]
                                         $("#day").blur(function (){ // focus를 잃을때
                                            var year = $("#year").val();
                                            var month = $("#month").val();
                                            var day = $("#day").val();
                                            var birth = `\${year}-\${month}-\${day}`;
                                            $("#birth").val( birth );
                                            // alert(  $("#birth").val() );
                                         });
                                         */
                                       </script>
	                                <span class="moon">
	                                    <input type="radio" name="is_lunar" value="Solar" id="IsSolar" checked />양력
	                                    <input type="radio" name="is_lunar" value="Lunar" id="IsLunar" />음력
	                                    <!-- <input type="hidden" name="is_lunar"> -->
	                                </span>
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									핸드폰 번호
								</dt>
								<dd class="join-form-data">
									<input type="text" name="cphone" value="010-2222-3333"/><span>[대시(-)를 포함할 것: 예) 010-3456-2934]</span>
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									이메일
								</dt>
								<dd class="join-form-data">
									<input type="text" name="email" value="hgd@name.com" />
								</dd>
							</dl>
							<dl class="join-form-row">
								<dt class="join-form-title">
									취미
								</dt>
								<dd class="join-form-data habit">
									<input type="checkbox" name="habit" id="music" value="music" checked="checked"/><label for="music">음악</label>
									<input type="checkbox" name="habit" id="movie" value="movie" checked="checked"/><label for="movie">영화</label>
									<input type="checkbox" name="habit" id="trip" value="trip" /><label for="trip">여행</label>
									<!-- <input type="hidden" name="habits"> -->
								</dd>
							</dl>						
						</div>
					<div id="buttonLine">
						<input class="btn-okay button" type="submit" value="가입" />
					</div>
					</form>	
				</div>
				<div id="navi">
					<h2>회원가입</h2>
					<h3 class="hidden">회원메뉴</h3>
					<ul id="navi-menu">
						<li>
							<a href="">로그인</a>
						</li>
						<li>
							<a href="" class="current">회원가입</a>
						</li>
						<li>
							<a href="">아이디찾기</a>
						</li>
						<li>
							<a href="">비밀번호 재발급</a>
						</li>
					</ul>
                       
					<h3 id="fav-title">추천사이트</h3>
					<ul class="margin-small">
						<li>
							<a href="http://www.answeris.net"><img src="../images/answeris.png" alt="앤서이즈" /></a>
						</li>
						<li>
							<a href="http://www.microsoft.com"><img src="../images/microsoft.png" alt="마이크로소프트" /></a>
						</li>
						<li>
							<a href="http://www.w3c.org"><img src="../images/w3c.png" alt="W3C" /></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="footer">
			<div class="top-wrapper">
				<h2><img src="../images/footerLogo.png" alt="뉴렉처"/></h2>
				<p>				
					<address id="ad">
						사업자등록번호 : 000-00-00000000 통신판매업신고 : 서울 0000-000 관리자 : 홍길동
						<br/>
						주소 : 서울시 000구 001동 000-0 00빌딩 0층 전화 : 02-000-0000 팩스 : 02-000-0000
					</address>
				</p>				
				<p>
					Copyright ⓒ newlecture.com 2012-2012 All Right Reserved. Contact master@newlecture.com for more information
				</p>
			</div>
		</div>
		<script>
		/*
			$("#buttonLine > input").on("click", function(){
				event.preventDefault();
	
				let realBirth = $("#year").val()+"-" + $("#month").val().padStart(2, '0')+"-" + $("#day").val().padStart(2, '0');
				$(":hidden[name='birth']").val(realBirth);
			
				let is_lunar = $(":radio:checked").val();
				$(":hidden[name='is_lunar']").val(is_lunar);
				
				// let strHabits = $(":checkbox[name='habit']:checked").map(function(){
				//	return $(this).val()
				//}).get().join(", ");
				//$(":hidden[name='habits']").val(strHabits); 
				
				$("form").submit();
			}) //$(".btn-okay button").on("click", function(){
				*/
		</script>
	</body>
</html>
