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

        initialize: function (options) {
            this.collection = options.collection;
        },

        onRender: function() {
            this.form = this.$('form');
            this.form.putFocus();
        },

        onSave: function(e) {
            e.preventDefault();

            this.form.hideFieldErrors();

            var account = new Account.Model();

            Helpers.subscribeModelInvalidEvent(account, this.form);

            var attributes = _.extend(this.form.serializeFields(), {
                'createdByUserId': Application.context.user.get('id')
            });

            if (!account.set(attributes, { validate: true })) {
                return;
            }

            var self = this;

            self.collection.create(account, {
                wait: true,
                validate: false,
                success: function() {
                    Application.vent.trigger('account:created', account);
                },
                error: function(model, jqxhr) {
                    if (Helpers.hasModelErrors(jqxhr)) {
                        var modelErrors = Helpers.getModelErrors(jqxhr);
                        if (modelErrors) {
                            self.form.showFieldErrors({
                                errors: modelErrors
                            });
                            return;
                        }
                    }
                }
            });
        }
    });

    return AccountCreateView;
});
