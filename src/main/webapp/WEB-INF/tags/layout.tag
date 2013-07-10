<%@ tag description="Layout template" pageEncoding="UTF-8"%>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ tag import="org.joda.time.DateTime" %>
<% request.setAttribute("now", DateTime.now()); %>
<!DOCTYPE html>
<html>
    <head>
        <meta lang="en"/>
        <title>My Finances</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/public/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/public/css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link href="/public/css/main.css" rel="stylesheet" />
    </head>
    <body>

        <div id="wrap">
            <jsp:invoke fragment="header"/>

            <div id="body" class="container">
                <jsp:doBody/>
            </div>

            <jsp:invoke fragment="footer"/>

            <div id="push"></div>
        </div>

        <footer id="footer">
            <p class="copy">All Content &copy; ${now.getYear().toString()} Sam Moore</p>
        </footer>
    </body>
</html>
