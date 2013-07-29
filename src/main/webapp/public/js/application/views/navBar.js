var Application;

(function(_, Backbone, Marionette, Application) {
    var Views = Application.Views || (Application.Views = {});

    Views.NavBar = Backbone.Marionette.ItemView.extend({
        template: '#navBar',

        onRender: function () {
            console.log('navBar');
        },

        select: function(currentPage) {
            return this.deselectAll().filter('.' + currentPage).addClass('active');
        },

        deselectAll: function() {
            return this.$('.nav > li').removeClass('active');
        },
    });

})(_, Backbone, Marionette, Application || (Application = {}));
