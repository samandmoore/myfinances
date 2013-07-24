var Application;

(function ($, _, Backbone, Application) {

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

    function start(options) {
        Application.context = new Application.Context(options);

        Application.router = new Application.Router({
            context: Application.context
        });

        Backbone.history.start();

        if (!hasClientUrl()) {
            redirectToDefault();
        }
    }

    Application.clientUrl = clientUrl;
    Application.start = start;

})(jQuery, _, Backbone, Application || (Application = {}));
