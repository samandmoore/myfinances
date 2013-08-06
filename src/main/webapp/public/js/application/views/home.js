define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var Home = Marionette.ItemView.extend({
        template: '#home'
    });

    return Home;
});
