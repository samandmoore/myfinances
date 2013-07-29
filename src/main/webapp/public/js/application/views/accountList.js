var Application;

(function ($, _, Backbone, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.AccountListItem = Marionette.ItemView.extend({});

    Views.AccountList = Marionette.CollectionView.extend({
        template: '#account-list',

        el: '#account-list',

        initialize: function (options) {
            this.router = options.router;
        }
    });

})(jQuery, _, Backbone, Application || (Application = {}));
