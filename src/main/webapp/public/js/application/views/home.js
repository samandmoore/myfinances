var Application;

(function(_, Backbone, Marionette, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.Home = Backbone.Marionette.ItemView.extend({
        template: '#home',
        onRender: function () {
            console.log('home');
        }
    });

})(_, Backbone, Marionette, Application || (Application = {}));
