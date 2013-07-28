var Application;

(function ($, _, Backbone, Marionette, Application) {

    function hasClientUrl() {
        var hash = window.location.hash;

        if (hash.length > Application.clientUrlPrefix.length) {
            return true;
        }

        if (Application.clientUrlPrefix.indexOf(hash) === 0) {
            return false;
        }

        return true;
    }

    function redirectToDefault() {
        Application.router.navigate(clientUrl('/'), true);
    }

    function clientUrl() {
        var path = _.toArray(arguments).join('/');

        if (path.length && path.indexOf('/') === 0) {
            path = path.substring(1);
        }

        return Application.clientUrlPrefix + path;
    }

    function configureHashUrls() {
        $(document).on('click', 'a', function (e) {
            var href = $(this).attr('href');

            e && e.preventDefault();

            Application.router.navigate(href.substring(1), true);
        });
    }

    Application.addInitializer(function(options) {
        Application.addRegions({
            userLinks: '#userLinks', // top right - user's name, profile, logout
            navBar: '#navBar', // main navigation at top
            mainContent: '#mainContent' // the container where we place the contents for each page,
        });
    });

    /**
    * override the marionette template loader
    */
    Marionette.TemplateCache.prototype.loadTemplate = function (templateId) {
        var self = this,
            url = null,
            promise = null,
            result = null;

        if (templateId) {

            templateId = templateId.replace("#", "");

            url = "/public/templates/" + templateId + ".html?" + new Date().getTime();
            promise = $.ajax({
                url: url,
                async: false
            });

            promise.done(function(templateHtml) {
                var $template = $(templateHtml);
                result = $template.html();
            });
        } else {
            result = '';
        }

        return result;
    }

    Application.on('start', function onStart(options) {
        Application.context = new Application.Context(options);

        Application.router = new Application.Router({
            context: Application.context
        });

        configureHashUrls();

        Backbone.history.start({ pushState: true });

        if (!hasClientUrl()) {
            redirectToDefault();
        }
    });

    Application.clientUrl = clientUrl;

}(jQuery, _, Backbone, Marionette, Application));
