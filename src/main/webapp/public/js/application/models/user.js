var Application;

(function (_, Backbone, Application) {
    var Models = Application.Models || (Application.Models = {});

    Models.User = Backbone.Model.extend({
        urlRoot: function () {
            return Application.serverUrlPrefix + "/users";
        }
    })


}(_, Backbone, Application || (Application = {})));
