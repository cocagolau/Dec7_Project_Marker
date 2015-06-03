<%@page session="true"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<title>admin</title>
</head>
<body>
	<h3>Login with Username and Password</h3>
	<form name='f' action='/login' method='POST'>
		<table>
			<tr><td>User:</td><td><input type='text' name='username' value=''></td></tr>
			<tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
			<tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
			<!-- <input name="_csrf" type="hidden" value="081aea53-6eff-4ce7-8a23-f2721e38b331" /> -->
		</table>
	</form>
</body>
</html>