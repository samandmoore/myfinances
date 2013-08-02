define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/models/account',
    'application/models/user'
],
function($, _, Backbone, Marionette, Account, User) {

    var Context = (function() {

        function Context(options) {
            this.accounts = new Account.Collection();
            this.user = new User.Model();

            if (options.userSignnedIn) {
                this.userSignedIn({ load: true });
            }
        }

        Context.prototype = {
            isUserSignedIn: function() {
                return this.signedIn;
            },

            userSignedIn: function (options) {
                var self = this;
                options || (options = { load: true });

                self.signedIn = true;

                if (options.load) {
                    // fetch user details
                    var userResult = self.user.fetch({ reset: true });

                    userResult.done(function (model) {
                        var currentUserId = self.user.get('id');

                        // fetch accounts
                        self.accounts.fetch({
                            reset: true,
                            data: {
                                forUserId: currentUserId
                            }
                        });
                    });
                }
            },

            userSignedOut: function() {
                this.signedIn = false;
                this.user = new User();
                this.accounts.reset();
             }
        };

        return Context;
    })();

    return Context;
});
