<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property='title' /></title>
	<sitemesh:write property='head' />
</head>
<body>
	<div id="header">
		<h1 class="header-logo">
			<a href="/"><span  class="">Marker</span></a>
		</h1>
	</div>
	
	<div id="container">
		<sitemesh:write property='body' />
	</div>
</body>

</html>