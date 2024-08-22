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
        <button>Login</button>
    </form>
</body>
</html>