var Application;

(function (_, Backbone, Application) {
    var Models = Application.Models || (Application.Models = {});

    Models.NavBarLink = Backbone.Model.extend({});

    Models.NavBarLinks = Backbone.Collection.extend({
        model: Models.NavBarLink
    });

}(_, Backbone, Application || (Application = {})));
