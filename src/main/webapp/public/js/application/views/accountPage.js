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

        events: {
            'click #new-account': 'showAddNewAccount'
        },

        perspectives: {
            'list': null,
            'create': null
        },

        initialize: function (options) {
            var self = this;

            self.router = options.router;
            self.accounts = options.accounts;

            self.perspectives.list = new AccountListView({
                collection: self.accounts
            });

            self.perspectives.create = new AccountCreateView();

            this.currentPerspective = options.perspective || 'list';
        },

        onRender: function () {
            this.contentPane.show(this.perspectives[this.currentPerspective]);
        },

        showAddNewAccount: function (e) {
            e.preventDefault();

            this.router.navigate('accounts/new');

            this.contentPane.show(this.perspectives.create);
        }
    });

    return AccountPageLayout;
});
