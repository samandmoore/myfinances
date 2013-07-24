var Application;

(function (_, Backbone, Application) {
    var Models = Application.Models || (Application.Models = {});

    Models.Account = Backbone.Model.extend({
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

    Models.Accounts = Backbone.Collection.extend({
        model: Models.Account,

        url: function () {
            return Application.serverUrlPrefix + '/accounts';
        }
    });

     _.extend(Models.Account.prototype, Application.Models.Validation);

})(_, Backbone, Application || (Application = {}));
