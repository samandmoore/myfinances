define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/basePage',
    'application/views/accounts/list',
    'application/views/accounts/create'
],
function($, _, Backbone, Marionette, BasePage, AccountListView, AccountCreateView) {

    var AccountPageLayout = BasePage.extend({
        template: '#accounts/layout',

        regions: {
            contentPane: '#account-content'
        },

        // view ctors for each type of view may show
        perspectives: {
            'list': AccountListView,
            'create': AccountCreateView
        },

        onPageInitialize: function (options) {
            this.bindToEvents();
        },

        bindToEvents: function () {
            Application.vent.on('account:created', this.onAccountCreated, this);
        },

        onAccountCreated: function (account) {
            this.setPerspective('list');
            this.router.navigate('/accounts');
            this.onRender();
        }
    });

    return AccountPageLayout;
});
