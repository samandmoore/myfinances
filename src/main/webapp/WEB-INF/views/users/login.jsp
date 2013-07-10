<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
        <h1>Sign In</h1>
    </jsp:attribute>

    <jsp:body>
        <form action="/users/login" method="POST" class="form-horizontal">
            <div class="control-group">
                <label class="control-label" for="username">Email / Username</label>
                <div class="controls">
                    <input type="text" name="username" placeholder="Email / Username">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="password">Password</label>
                <div class="controls">
                    <input type="password" name="password" placeholder="Password">
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn">Sign in</button>
                </div>
            </div>
        </form>
    </jsp:body>

</t:layout>
