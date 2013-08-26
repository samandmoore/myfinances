define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/navBar',
    'application/views/home',
    'application/views/accounts/page',
    'application/views/categories/page'
],
function($, _, Backbone, Marionette, NavBarView, HomePage, AccountPage, CategoriesPage) {

    var Router = Backbone.Router.extend({
        routes: {
            '': 'home',
            'accounts': 'accounts',
            'accounts/new': 'createAccount',
            'categories': 'categories'
        },

        initialize: function (options) {
            this.context = options.context;

            this.initializeViews(options);
        },

        initializeViews: function (options) {
            this.navBarView = new NavBarView();

            this.homePage = new HomePage();

            this.accountPage = new AccountPage({
                pageCollection: this.context.accounts,
                router: this
            });

            this.categoriesPage = new CategoriesPage({
                // pageCollection: this.context.categories,
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

        categories: function () {
            this.categoriesPage.setPerspective('list');

            this.activate(
                this.categoriesPage,
                'categories'
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
