<!DOCTYPE html>
<html>
	<head>
		<title>sign up</title>
	</head>
	<body>
		<div id="header">
			<h1>Social Sign Up</h1>
		</div>
		
		<div id="content">
			<h3>Sign Up</h3>
			
			<form id="signup" action="/signup" method="post">
			
				<label for="firstName">First Name</label>
				<input type="text" value="${form.firstName!""}" />
				
				<label for="lastName">Last Name</label>
				<input type="text" value="${form.lastName!""}" />
				
				<label for="username">Username</label>
				<input type="text" value="${form.username!""}" />
				
				<label for="password">Password (at least 6 characters)</label>
				<input type="password" value="${form.password!""}" />
				
				<p><button type="submit">Sign Up</button></p>
			</form>
		</div>
	</body>
</html>

