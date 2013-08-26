define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/accountList',
    'application/views/accountCreate'
],
function($, _, Backbone, Marionette, AccountListView, AccountCreateView) {

    var AccountPageLayout = Backbone.Marionette.Layout.extend({
        template: "#accounts/layout",

        regions: {
            contentPane: "#account-content"
        },

        // view ctors for each type of view may show
        perspectives: {
            'list': AccountListView,
            'create': AccountCreateView
        },

        initialize: function (options) {
            this.router = options.router;
            this.accounts = options.accounts;

            this.currentPerspective = options.perspective || 'list';

            this.bindToEvents();
        },

        bindToEvents: function () {
            Application.vent.on('account:created', this.onAccountCreated, this);
        },

        onRender: function () {
            var self = this,
                ctor = self.perspectives[this.currentPerspective];

            self.contentPane.show(
                new ctor({
                    collection: self.accounts
                })
            );
        },

        onAccountCreated: function (account) {
            this.setPerspective('list');
            this.router.navigate('/accounts');
            this.onRender();
        },

        setPerspective: function (perspective) {
            this.currentPerspective = perspective;
        }
    });

    return AccountPageLayout;
});
