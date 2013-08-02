define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/lib/constants'
],
function($, _, Backbone, Marionette, Constants) {

    var User = Backbone.Model.extend({
        urlRoot: function () {
            return Constants.serverUrlPrefix + "/users";
        }
    })

    return {
        Model: User
    };
});
