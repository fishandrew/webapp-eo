<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<metaname ="viewport"content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<style>
body {
	margin: 0;
	padding: 0;
	background-image: url('image/home-background.jpg'); /* 배경 이미지 설정 */
	background-size: cover; /* 이미지가 화면을 덮도록 설정 */
	background-position: center; /* 이미지가 가운데에 위치하도록 설정 */
	font-family: 'Arial', sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh; 
	}
.login-form { background-color : rgba( 255, 255, 255, 0.9); /* 투명도 있는 배경 */
	padding: 40px;
	border-radius: 10px;
	box-shadow: 0 0 15pxrgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 400px;
}

.login-formh1 {
	margin-bottom: 10px;
	color: #007bff;
	font-size: 28px;
	text-align: center;
}

.login-formh2 {
	margin-bottom: 20px;
	color: #007bff;
	font-size: 24px;
}

.login-forminput[type="text"], .login-forminput[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

.login-forminput[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
}

.login-forminput[type="submit"]:hover {
	background-color: #0056b3;
}

.error-message {
	color: red;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="login-form">
		<!-- 시스템 이름 -->
		<h1>Webapp eo</h1>
		<!-- login form -->
		<h2>Login</h2>
		<!-- out error msg -->
		<c:if test="${not empty error}">
			<p class="error-message">${error }</p>
		</c:if>
		<form action="login" method="post">
			<input type='text' name='username' placeholder="Username" required>
			<input type='password' name='password' placeholder="Password" required>
			<input type="submit" value="Login">
		</form>

	</div>
</body>
</html>