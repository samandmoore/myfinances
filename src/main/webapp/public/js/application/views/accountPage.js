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
        template: "#account-page-layout",

        regions: {
            contentPane: "#account-content"
        },

        perspectives: {
            'list': null,
            'create': null
        },

        initialize: function (options) {
            var self = this;

            self.router = options.router;
            self.accounts = options.accounts;

            self.perspectives.list = AccountListView;
            self.perspectives.create = AccountCreateView;

            this.currentPerspective = options.perspective || 'list';

            this.bindToEvents();
        },

        bindToEvents: function () {
            Application.vent.on('account:created', this.onAccountCreated, this);
        },

        onRender: function () {
            var self = this,
                ctor = this.perspectives[this.currentPerspective];

            this.contentPane.show(
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
