define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/models/validation',
    'application/lib/constants'
],
function($, _, Backbone, Marionette, Validation, Constants) {

    var Account = Backbone.Model.extend({
        defaults: function () {
            return {
                name: null,
                balance: 0
            };
        },

        validate: function (attributes) {
            if (!attributes.name) {
                this.addError('name', 'Name is required.');
            }

            if (attributes.balance == null) {
                this.addError('balance', 'Balance is required.');
            } else {
                var balance = parseFloat(attributes.balance.toString());

                if (!_.isFinite(balance)) {
                    this.addError('balance', 'Invalid balance, must be decimal value.');
                } else {
                    if (balance < 0) {
                        this.addError('balance', 'Balance cannot be negative.');
                    }
                }
            }

            return _.isEmpty(errors) ? void(0) : errors;
        }
    });

    var Accounts = Backbone.Collection.extend({
        model: Account,

        url: function () {
            return Constants.serverUrlPrefix + '/accounts';
        }
    });

     _.extend(Account.prototype, Validation);

     return {
        Model: Account,
        Collection: Accounts
     };
});
