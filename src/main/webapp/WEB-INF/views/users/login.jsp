<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta lang="en"/>
        <title>My Finances</title>
    </head>

    <body>
        <h1>Sign in</h1>

        <form action="/users/login" method="POST">
            <label>Email</label>
            <input type="text" name="username" />

            <label>Password</label>
            <input type="password" name="password" />

            <button type="submit">Sign In</button>
        </form>
    </body>
</html>
</html>