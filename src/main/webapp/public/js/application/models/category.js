define(
[
    'backbone',
    'application/models/validation',
    'application/lib/constants'
],
function (Backbone, Validation, Constants) {
    var CategoryType = {
        CREDIT: 'CREDIT',
        DEBIT: 'DEBIT'
    };

    var Category = Backbone.Model.extend({
        defaults: {
            title: null,
            type: null
        },

        validate: function(attributes) {
            if (!attributes.title) {
                this.addError('title', 'Title is required.');
            }

            if (attributes.type) {
                if (!_.include(_.values(CategoryType), attributes.type)) {
                    this.addError('type', 'Type must be any of the following (' + _.values(CategoryType).join(', ') + ') value.');
                }
            } else {
                this.addError('type', 'Type is required.');
            }

            return _.isEmpty(this.errors) ? null : this.errors;
        }
    });

    var Categories = Backbone.Collection.extend{
        model: Category,

        url: function () {
            return Constants.serverUrlPrefix + '/categories';
        }
    });

    _.extend(Category.prototype, Validation);

    return {
        Type: CategoryType,
        Model: Category,
        Collection: Categories
    };
});
