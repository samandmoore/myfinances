var Application;

(function($, _, Backbone, Application) {

    Application.Router = Backbone.Router.extend({
        routes: {
            '!/': 'home',
            '*path': 'notFound'
        },

        initialize: function (options) {
            this.context = options.context;
        },

        home: function() {
            this.activate(this.homeView, 'home');
        },

        notFound: function() {
            this.activate(this.notFoundView);
        },

        /**
        * @param view - the view object to activate
        * @param menu - an optional menu item name to set as active
        */
        activate: function (view, menu) {
            if (this.currentView) {
                if (this.currentView === view) {
                    return;
                }
                // deactivate the current view
                this.currentView.deactivate();
            }

            // set up the active menu item...

            this.currentView = view;
            // activate the current view
            Application.mainContent.show(view);
        }
    });

})(jQuery, _, Backbone, Application || (Application = {}));
