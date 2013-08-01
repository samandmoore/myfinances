var Application;

(function ($, _, Backbone, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.AccountListItem = Marionette.ItemView.extend({
        template: '#account-list-item',
        tagName: 'tr'
    });

    Views.AccountList = Marionette.CompositeView.extend({
        template: '#account-list',

        itemView: Views.AccountListItem,

        itemViewContainer: 'tbody',

        emptyView: Backbone.Marionette.ItemView.extend({
                        tagName: 'tr',
                        template: "#account-list-empty"
                    }),

        initialize: function (options) {
            this.router = options.router;
        },

        onRender: function () {
            console.log('accounts list');
        }
    });

})(jQuery, _, Backbone, Application || (Application = {}));
