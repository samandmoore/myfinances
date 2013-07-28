var Application;

(function(_, Backbone, Marionette, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.NavBar = Backbone.Marionette.ItemView.extend({
        template: '#navBar',
        onRender: function () {
            console.log('navBar');
        }
    });

})(_, Backbone, Marionette, Application || (Application = {}));
