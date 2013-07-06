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
    </head>
    <body>
        <header id="header">
            <jsp:invoke fragment="header"/>
        </header>

        <div id="body">
            <jsp:doBody/>
        </div>

        <jsp:invoke fragment="footer"/>

        <footer id="footer">

            <p id="copyright">All Content &copy; ${now.getYear().toString()} Sam Moore</p>
        </footer>
    </body>
</html>
