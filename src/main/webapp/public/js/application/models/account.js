var Application;

(function (_, Backbone, Application) {
    var Models = Application.Models || (Application.Models = {});

    Models.Account = Backbone.Model.extend({
        defaults: function () {
            return {
                name: null,
                balance: 0
            };
        }
    });

    Models.Accounts = Backbone.Collection.extend({
        model: Models.Account,

        url: function () {
            return Application.serverUrlPrefix + '/accounts';
        }
    });

})(_, Backbone, Application || (Application = {}));
