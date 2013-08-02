define(
[
    'jquery',
    'underscore',
    'backbone'
],
function($, _, Backbone) {

    var Validation = {
        errors: {},
        addError: function(attribute, message) {
            return (this.errors[attribute] || (this.errors[attribute] = [])).push(message);
        }
    };

    return Validation;
});
