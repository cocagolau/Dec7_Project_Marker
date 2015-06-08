<!DOCTYPE html>
<html lang="KO">
<head>
	<title>sign in</title>
</head>
<body>
	<div id="header">
		<h1>Social Sign In</h1>
	</div>
	
	<div id="content">
		<form id="signin" action="/signin/authenticate" method="post">
			<div class="formInfo">
			</div>
			<fieldset>
				<label for="login">Username</label>
				<input id="login" name="username" type="text" size="25"></input>
				<label for="password">Password</label>
				<input id="password" name="password" type="password" size="25"></input>
			</fieldset>
			<button type="submit">Sign In</button>
			
			<p>Some test user/password pairs you may use are:</p>
			<ul>
				<li>habuma/freebirds</li>
				<li>kdonald/melbourne</li>
				<li>rclarkson/atlanta</li>
			</ul>
			
			<p>Or you can <a href="/signup">signup</a> with a new account.</p>
		</form>

	
		<!-- FACEBOOK SIGNIN -->
		<form name="fb_signin" id="fb_signin" action="/signin/facebook" method="POST">
			<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access"></input>
			<button type="submit">facebook submit</button>
		</form>

	</div>
</body>
</html>
