<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
        <h1>Welcome</h1>
    </jsp:attribute>

    <jsp:body>
        <t:userdetails user="${model.userDetails}"/>

        <p>single page app goes here!</p>
    </jsp:body>

</t:layout>
