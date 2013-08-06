define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/models/account',
    'application/views/helpers'
],
function($, _, Backbone, Marionette, Account, Helpers) {
    var AccountCreateView = Backbone.Marionette.ItemView.extend({
        template: '#account-new',

        events: {
            'submit form': 'onSave'
        },

        initialize: function(options) {
            this.form = this.$('form');
        },

        onRender: function() {
            this.form.putFocus();
        },

        onSave: function(e) {
            e.preventDefault();

            this.form.hideFieldErrors();

            var account = new Account.Model();

            // Helpers.subscribeModelInvalidEvent(account, this.form);

            var attributes = _.extend(this.form.serializeFields(), {
                createdAt: new Date()
            });

            if (!account.set(attributes, { validate: true })) {
                return;
            }

            var self = this;

            this.collection.create(account, {
                wait: true,
                validate: false,
                success: function() {
                    self.router.navigate(
                        Application.clientUrl('/accounts'),
                        true);
                    $.showSuccessbar('New account created.');
                },
                error: function(model, jqxhr) {
                    if (Views.Helpers.hasModelErrors(jqxhr)) {
                        var modelErrors = Views.Helpers.getModelErrors(jqxhr);
                        if (modelErrors) {
                            self.form.showFieldErrors({
                                errors: modelErrors
                            });
                            return;
                        }
                    }
                    $.showErrorbar('An unexpected error has occurred while ' +
                        'creating new account.');
                }
            });
        }
    });

    return AccountCreateView;
});
