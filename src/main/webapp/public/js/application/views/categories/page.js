define(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/views/basePage'
],
function($, _, Backbone, Marionette, BasePage) {

    var CategoriesPageLayout = BasePage.extend({
        template: '#categories/layout',

        regions: {
            contentPane: '#categories-content'
        },

        // view ctors for each type of view may show
        perspectives: {
            'list': ''
        }
    });

    return CategoriesPageLayout;
});
