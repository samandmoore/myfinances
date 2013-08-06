define(
[
],
function() {

    var Helpers = {
        hasModelErrors: function(jqxhr) {
            return jqxhr.status === 409;
        },

        getModelErrors: function(jqxhr) {
            var response = jqxhr.responseJSON;

            if (!response) {
                return void(0);
            }

            /* format
            {
              "errors": {
                "name": [
                  "too short",
                  "bad choice",
                  "i don't like it"
                ]
              },
              "statusCode": 409,
              "statusText": "Conflict"
            }
            */

            return response.errors ?
                response.errors :
                void(0);
        },

         subscribeModelInvalidEvent: function(model, el) {
            model.once('invalid', function() {
                el.showFieldErrors({
                    errors: model.validationError
                });
            });
        },
    };

    return Helpers;

});
