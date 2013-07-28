<%@tag description="Debug Api template" pageEncoding="UTF-8"%>

<select id="method">
    <option>GET</option>
    <option>POST</option>
</select>

<input type="text" id="url" />

<textarea id="requestData"></textarea>

<button id="submit">submit</button>

<pre><code id="results"></code></pre>

<script type="text/javascript">
    jQuery(function () {
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
    });
</script>
