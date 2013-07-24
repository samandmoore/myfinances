var Application;

(function (Application) {

    Application.Context = (function() {

        function Context(options) {
            if (options.userSignnedIn) {
                this.userSignedIn({ load: false });
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
                    // fetch accounts
                }
            },

            userSignedOut: function() {
                this.signedIn = false;
             }
        };

        return Context;
    })();

})(Application || (Application = {}));
