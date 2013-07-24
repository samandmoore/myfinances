<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <jsp:attribute name="header">
        <t:navbar/>
    </jsp:attribute>

    <jsp:attribute name="bodyScripts">
        <script type="text/javascript" src="/public/js/json2.js"></script>
        <script type="text/javascript" src="/public/js/underscore-min.js"></script>
        <script type="text/javascript" src="/public/js/backbone-min.js"></script>
        <script type="text/javascript" src="/public/js/application/context.js"></script>
        <script type="text/javascript" src="/public/js/application/router.js"></script>
        <script type="text/javascript" src="/public/js/application/application.js"></script>
        <script type="text/javascript">
            (function () {
                $('#submit').on('click', function (e) {
                    e.preventDefault();

                    var url = $('#url').val(),
                        requestData = $('#requestData').val(),
                        method = $('#method').val(),
                        $resultArea = $('#results'),
                        options = {};

                    options.url = url;
                    options.contentType = 'application/json; charset=UTF-8';
                    options.method = method;

                    if (requestData) {
                        options.data = requestData;
                        options.dataType = 'json';
                    }

                    var request = $.ajax(options);

                    request.done(function (data) {
                        $resultArea.text(JSON.stringify(data, undefined, 2));
                    });

                    request.fail(function (xhr, textStatus, errorThrown) {
                        var result = 'Response: ' + xhr.status + ' ' + textStatus + ' ... ' + errorThrown;

                        if (xhr.responseJSON) {
                            result += '<br />';
                            result += JSON.stringify(xhr.responseJSON, undefined, 2);
                        }

                        $resultArea.html(result);
                    });
                });
            }());
        </script>
        <script type="text/javascript">
            app = Application;
            app.clientUrlPrefix = '/';
            app.serverUrlPrefix = '/api/';
            jQuery(function() {
              app.start({
                userSignnedIn: true,
                accounts: []
              });
            });
        </script>
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

        <pre><code id="results"></code></pre>
    </jsp:body>

</t:layout>
