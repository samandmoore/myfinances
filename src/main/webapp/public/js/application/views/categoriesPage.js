define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var CategoriesPageLayout = Backbone.Marionette.Layout.extend({
        template: "#categories-page-layout",

        regions: {
            contentPane: "#categories-content"
        },

        // view ctors for each type of view may show
        perspectives: {
            'list': ''
        },

        initialize: function (options) {
            this.router = options.router;

            this.currentPerspective = options.perspective || 'list';
        },

        onRender: function () {
            var self = this,
                ctor = self.perspectives[this.currentPerspective];

            self.contentPane.show(
                new ctor({
                    // collection: self.accounts
                })
            );
        },

        setPerspective: function (perspective) {
            this.currentPerspective = perspective;
        }
    });

    return CategoriesPageLayout;
});
