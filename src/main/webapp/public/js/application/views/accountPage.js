define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/accountList'
],
function($, _, Backbone, Marionette, AccountListView) {

    var AccountPageLayout = Backbone.Marionette.Layout.extend({
        template: "#account-page-layout",

        regions: {
            accountContent: "#account-content"
        },

        initialize: function (options) {
            var self = this;
            this.router = options.router;
            this.accounts = options.accounts;

            this.accountListView = new AccountListView({
                collection: self.accounts
            });
        },

        onRender: function () {
            this.accountContent.show(this.accountListView);
        }
    });

    return AccountPageLayout;
});
