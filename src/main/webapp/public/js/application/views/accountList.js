define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var AccountListItem = Marionette.ItemView.extend({
        template: '#accounts/list-item',
        tagName: 'tr'
    });

    var AccountList = Marionette.CompositeView.extend({
        template: '#accounts/list',

        itemView: AccountListItem,

        itemViewContainer: 'tbody',

        emptyView: Marionette.ItemView.extend({
                        tagName: 'tr',
                        template: "#accounts/list-empty"
                    }),

        initialize: function (options) {
            this.router = options.router;
        }
    });

    return AccountList;
});
