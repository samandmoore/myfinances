var Application;

(function (Application) {

    Application.Context = (function() {

        function Context(options) {
            this.accounts = new Application.Models.Accounts();
            this.user = new Application.Models.User();

            if (options.userSignnedIn) {
                this.userSignedIn({ load: true });
            }
        }

        Context.prototype = {
            isUserSignedIn: function() {
                return this.signedIn;
            },

            userSignedIn: function (options) {
                options || (options = { load: true });

                this.signedIn = true;

                if (options.load) {
                    // fetch user details
                    this.user.fetch({ reset: true });

                    // fetch accounts
                    this.accounts.fetch({ reset: true });
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

})(Application || (Application = {}));
