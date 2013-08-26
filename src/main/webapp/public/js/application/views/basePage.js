define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette'
],
function($, _, Backbone, Marionette) {

    var BasePageLayout = Backbone.Marionette.Layout.extend({

        // view ctors for each type of view may show
        perspectives: { },

        initialize: function (options) {
            this.router = options.router;
            this.pageCollection = options.pageCollection;

            this.currentPerspective = options.perspective || 'list';

            this.onPageInitialize(options);
        },

        onPageInitialize: function () {
            // no-op ... can be overriden in inheriting views
        },

        onBeforePageRender: function () {
            // no-op ... can be overriden in inheriting views
        },

        onRender: function () {
            this.onBeforePageRender();
            this.showCurrentPerspective();
            this.onAfterPageRender();
        },

        onAfterPageRender: function () {
            // no-op ... can be overriden in inheriting views
        },

        showCurrentPerspective: function () {
            var self = this,
                ctor = self.perspectives[this.currentPerspective];

            self.contentPane.show(
                new ctor({
                    collection: this.pageCollection
                })
            );
        },

        setPerspective: function (perspective) {
            this.currentPerspective = perspective;
        }
    });

    return BasePageLayout;
});
