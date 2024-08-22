<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h1>Sign In Page</h1>
    <hr>
    <form action="/join" method="post">
        <input type="text" name="username" placeholder="username">
        <br>
        <input type="password" name="password" placeholder="password">
        <br>
        <input type="text" name="email" placeholder="email">
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button>Sign In</button>
    </form>
</body>
</html>