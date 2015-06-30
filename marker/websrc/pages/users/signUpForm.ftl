<!DOCTYPE html>
<html lang="KO">
<head>
<title>로그인</title>
</head>
<body>
	<h1 class="login-content-title">title</h1>
	<p class="login-content-welcome">welcome</p>
	<form:form action="" method="post" cssClass="signin-with-sns" modelAttribute="signUpForm">
		<p>SLiPP에서 사용할 닉네임을 입력해 주세요.</p>
		<form:input path="userId" cssClass="inp_nickname focused"/><br/>
		<form:errors path="*" />
		<button class="signin-with-sns-submit-btn" type="submit"><i class="icon-signin"></i> 회원가입</button>
	</form:form>
</body>
</html>
