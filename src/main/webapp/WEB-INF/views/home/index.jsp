<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
        <t:navbar/>
    </jsp:attribute>

    <jsp:body>
        <t:userdetails user="${model.userDetails}"/>

        <p>single page app goes here!</p>

        <select id="method">
            <option>GET</option>
            <option>POST</option>
        </select>

        <input type="text" id="url" />

        <textarea id="requestData"></textarea>

        <button id="submit">submit</button>

        <div id="results"></div>
    </jsp:body>

</t:layout>
