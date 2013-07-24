var Application;

(function (Application) {
    var Models = Application.Models || (Application.Models = {});

    Models.Validation = {
        errors: {},
        addError: function(attribute, message) {
            return (this.errors[attribute] || (this.errors[attribute] = [])).push(message);
        }
    };

})(Application || (Application = {}));
