<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Sign Up</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <label for="loginUsername">Username:</label>
        <input type="text" id="loginUsername" name="username" required><br><br>
        <label for="loginPassword">Password:</label>
        <input type="password" id="loginPassword" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>

    <h2>Sign Up</h2>
    <form action="SignUpServlet" method="post">
        <label for="signUpUsername">Username:</label>
        <input type="text" id="signUpUsername" name="username" required><br><br>
        <label for="signUpPassword">Password:</label>
        <input type="password" id="signUpPassword" name="password" required><br><br>
        <label for="signUpRole">Role:</label>
        <select id="signUpRole" name="role">
            <option value="admin">Admin</option>
            <option value="u_lim">User Limited</option>
        </select><br><br>
        <label for="signUpStartDate">Start Date:</label>
        <input type="date" id="signUpStartDate" name="startDate" required><br><br>
        <input type="submit" value="Sign Up">
    </form>
</body>
</html>
