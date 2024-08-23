<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Form page</title>
</head>
<body>
    <h1>Login Form</h1>
    <hr>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="username">
        <br>
        <input type="password" name="password" placeholder="password">
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button>Login</button>
    </form>
    <a href="/oauth2/authorization/google">Google Login</a>
    <a href="/joinForm">Sign In</a>
</body>
</html>