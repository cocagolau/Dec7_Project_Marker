<%@page session="true"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<title>admin</title>
</head>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
	
	<#if pageContext.request.userPrincipal.name != null>
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} |
			<a href="<@spring.url value="/j_spring_security_logout" />"> Logout</a>
		</h2>
	</#if>
</body>
</html>