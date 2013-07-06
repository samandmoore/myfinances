<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
        <h1>Sign In</h1>
    </jsp:attribute>

    <jsp:body>
        <form action="/users/login" method="POST">
            <label>Email</label>
            <input type="text" name="username" />

            <label>Password</label>
            <input type="password" name="password" />

            <button type="submit">Sign In</button>
        </form>
    </jsp:body>

</t:layout>
