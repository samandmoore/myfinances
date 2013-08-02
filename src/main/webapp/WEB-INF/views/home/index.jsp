<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
        <div class="container">

            <div class="masthead">
                <div class="clearfix">
                    <h3 class="muted pull-left">My Finances</h3>
                    <div class="pull-right">
                        <t:userdetails user="${model.userDetails}"/>
                    </div>
                </div>

                <nav id="navBar">
                </nav>
            </div>

        </div>
    </jsp:attribute>

    <jsp:attribute name="bodyScripts">
        <script data-main="/public/js/main" src="/public/js/require.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div>
            <div id="flash">
            </div>
            <section id="mainContent">
                <div>
                    <div class="barber-pole"></div>
                </div>
            </section>
        </div>
    </jsp:body>
</t:layout>
