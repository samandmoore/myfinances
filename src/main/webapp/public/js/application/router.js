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
function($, _, Backbone, Marionette, NavBarView, HomePage, AccountPage) {

    var Router = Backbone.Router.extend({
        routes: {
            '': 'home',
            'accounts': 'accounts',
            'accounts/new': 'createAccount'
        },

        initialize: function (options) {
            this.context = options.context;

            this.initializeViews(options);
        },

        initializeViews: function (options) {
            this.navBarView = new NavBarView();

            this.homePage = new HomePage();

            this.accountPage = new AccountPage({
                accounts: this.context.accounts,
                router: this
            });
        },

        home: function() {
            this.activate(this.homePage, 'home');
        },

        accounts: function () {
            this.accountPage.setPerspective('list');

            this.activate(
                this.accountPage,
                'accounts'
            );
        },

        createAccount: function () {
            this.accountPage.setPerspective('create');

            this.activate(
                this.accountPage,
                'accounts'
            );
        },

        /**
        * @param view - the view object to activate
        * @param currentPage - an optional menu item name to set as active
        */
        activate: function (view, currentPage) {
            Application.navBar.show(this.navBarView);

            this.navBarView.select(currentPage);

            Application.mainContent.show(view);
        }
    });

    return Router;
});
