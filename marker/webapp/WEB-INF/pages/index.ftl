<html lang="KO">
<head>
<title>index</title>
</head>
<body>
	<div class="container">
		<h1>This is secured!</h1>
		<p>
			Hello <b>${pageContext.request.remoteUser}</b>
		</p>
		<@spring.url var="logoutUrl" value="/logout" />
		<form class="form-inline" action="${logoutUrl}" method="post">
			<input type="submit" value="Log out" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>