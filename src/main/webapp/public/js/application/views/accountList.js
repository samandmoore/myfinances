define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var AccountListItem = Marionette.ItemView.extend({
        template: '#account-list-item',
        tagName: 'tr'
    });

    var AccountList = Marionette.CompositeView.extend({
        template: '#account-list',

        itemView: AccountListItem,

        itemViewContainer: 'tbody',

        emptyView: Marionette.ItemView.extend({
                        tagName: 'tr',
                        template: "#account-list-empty"
                    }),

        initialize: function (options) {
            this.router = options.router;
        }
    });

    return AccountList;
});
