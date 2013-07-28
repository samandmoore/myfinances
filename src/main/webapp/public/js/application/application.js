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
    Marionette.TemplateCache.prototype.loadTemplate = function (templateId, callback) {
        if (templateId) {
            templateId = templateId.replace("#", "");
            var url = "/public/templates/" + templateId + ".html?" + new Date().getTime();
            var promise = $.ajax(url);
            promise.done(function(templateHtml) {
                var $template = $(templateHtml);
                var template = that.compileTemplate($template.html());
                callback(template);
            });
        } else {
            callback(function() {
                return '';
            });
        }
    }

    Application.on('start', function onStart(options) {
        Application.context = new Application.Context(options);

        Application.router = new Application.Router({
            context: Application.context
        });

        Backbone.history.start();

        if (!hasClientUrl()) {
            redirectToDefault();
        }
    });

    Application.clientUrl = clientUrl;

}(jQuery, _, Backbone, Marionette, Application));
