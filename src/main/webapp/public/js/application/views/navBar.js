define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var NavBar = Marionette.ItemView.extend({
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

    return NavBar;
});
