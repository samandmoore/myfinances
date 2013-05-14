<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta lang="en"/>
        <title>My Finances</title>
    </head>

    <body>
        <h1>Home page</h1>

        <ul>
            <li>${model.message}</li>
        </ul>

        <form action="/" method="POST">
            <input type="text" name="name" />
            <button type="submit">Say Hello</button>
        </form>
    </body>
</html>
</html>