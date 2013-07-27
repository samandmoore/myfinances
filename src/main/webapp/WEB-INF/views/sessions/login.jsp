<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:body>
        <style type="text/css">
          body {
            background-color: #f5f5f5;
          }

          .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 40px auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
               -moz-border-radius: 5px;
                    border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
               -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    box-shadow: 0 1px 2px rgba(0,0,0,.05);
          }
          .form-signin .form-signin-heading,
          .form-signin .checkbox {
            margin-bottom: 10px;
          }
          .form-signin input[type="text"],
          .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
          }
        </style>

        <form action="/sessions/login" method="POST" class="form-signin">

            <spring:hasBindErrors name="">
                <ul>
                    <c:forEach items="${errors.allErrors}" var="error">
                        <li>
                            <spring:message text="${error.defaultMessage}"/>
                        </li>
                    </c:forEach>
                </ul>
            </spring:hasBindErrors>

            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" name="username" class="input-block-level" placeholder="Email address">
            <input type="password" name="password" class="input-block-level" placeholder="Password">
            <button class="btn btn-large btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </jsp:body>

</t:layout>
