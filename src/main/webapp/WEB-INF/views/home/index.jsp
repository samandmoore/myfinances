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
        <!-- ext libs -->
        <script type="text/javascript" src="/public/js/json2.js"></script>
        <script type="text/javascript" src="/public/js/underscore.js"></script>
        <script type="text/javascript" src="/public/js/backbone-min.js"></script>
        <script type="text/javascript" src="/public/js/backbone.marionette.min.js"></script>
        <!--/ ext libs -->

        <!-- local libs -->
        <script type="text/javascript" src="/public/js/application/lib/form.js"></script>
        <!--/ local libs -->

        <!-- bootstrapper -->
        <script type="text/javascript" src="/public/js/application/main.js"></script>
        <!--/ bootstrapper -->

        <!-- models -->
        <script type="text/javascript" src="/public/js/application/models/validation-mixin.js"></script>
        <script type="text/javascript" src="/public/js/application/models/account.js"></script>
        <script type="text/javascript" src="/public/js/application/models/user.js"></script>
        <!--/ models -->

        <!-- views -->
        <script type="text/javascript" src="/public/js/application/views/helpers.js"></script>
        <script type="text/javascript" src="/public/js/application/views/navBar.js"></script>
        <script type="text/javascript" src="/public/js/application/views/home.js"></script>
        <script type="text/javascript" src="/public/js/application/views/accountList.js"></script>
        <!--/ views -->

        <!-- app -->
        <script type="text/javascript" src="/public/js/application/context.js"></script>
        <script type="text/javascript" src="/public/js/application/router.js"></script>
        <script type="text/javascript" src="/public/js/application/application.js"></script>
        <script type="text/javascript">
            app = Application;
            app.clientUrlPrefix = '/';
            app.serverUrlPrefix = ''; /* '/api/'; */
            jQuery(function() {
              app.start({
                userSignnedIn: true
              });
            });
        </script>
        <!--/ app -->
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
