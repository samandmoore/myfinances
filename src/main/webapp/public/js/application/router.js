var Application;

(function($, _, Backbone, Application) {

    Application.Router = Backbone.Router.extend({
        routes: {
            '': 'home',
            'accounts': 'accounts'
        },

        initialize: function (options) {
            this.context = options.context;

            this.navBar = new Application.Views.NavBar();
        },

        home: function() {
            console.log('home')
            this.activate(new Application.Views.Home(), 'home');
        },

        accounts: function () {
            console.log('accounts');
            this.activate(
                new Application.Views.AccountList({
                    collection: this.context.accounts,
                    router: this
                }),
                'accounts'
            );
        },

        /**
        * @param view - the view object to activate
        * @param menu - an optional menu item name to set as active
        */
        activate: function (view, currentPage) {
            if (this.currentView) {
                if (this.currentView === view) {
                    return;
                }
                // deactivate the current view
                // this.currentView.deactivate();
            }

            // set up the active menu item...
            Application.navBar.show(this.navBar);
            this.navBar.select(currentPage);

            this.currentView = view;
            // activate the current view
            Application.mainContent.show(view);
        }
    });

})(jQuery, _, Backbone, Application || (Application = {}));
