define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/navBar',
    'application/views/home',
    'application/views/accountPage'
],
function($, _, Backbone, Marionette, NavBarView, HomeView, AccountPage) {

    var Router = Backbone.Router.extend({
        routes: {
            '': 'home',
            'accounts': 'accounts',
            'accounts/new': 'createAccount'
        },

        initialize: function (options) {
            this.context = options.context;

            this.navBar = new NavBarView();
        },

        home: function() {
            this.activate(new HomeView(), 'home');
        },

        accounts: function () {
            this.activate(
                new AccountPage({
                    accounts: this.context.accounts,
                    router: this
                }),
                'accounts'
            );
        },

        createAccount: function () {
            this.activate(
                new AccountPage({
                    accounts: this.context.accounts,
                    router: this,
                    perspective: 'create'
                }),
                'accounts'
            );
        },

        /**
        * @param view - the view object to activate
        * @param currentPage - an optional menu item name to set as active
        */
        activate: function (view, currentPage) {
            if (this.currentView) {
                if (this.currentView === view) {
                    return;
                }
            }

            // set up the active menu item...
            Application.navBar.show(this.navBar);
            this.navBar.select(currentPage);

            // activate the current view
            this.currentView = view;
            Application.mainContent.show(view);
        }
    });

    return Router;
});
