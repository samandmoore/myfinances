var Application;

(function(_, Backbone, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.NotFound = Backbone.View.extend({
        el: '#not-found-page'
    });

})(_, Backbone, Application || (Application = {}));
