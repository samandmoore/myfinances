require.config({
    paths : {
        backbone: 'backbone',
        underscore: 'underscore',
        jquery: 'jquery-2.0.3',
        marionette: 'marionette',
        form: 'lib/form'
    },
    shim : {
        jquery : {
            exports : 'jQuery'
        },
        underscore : {
            exports : '_'
        },
        backbone : {
            deps : ['jquery', 'underscore'],
            exports : 'Backbone'
        },
        marionette : {
            deps : ['jquery', 'underscore', 'backbone'],
            exports : 'Marionette'
        }
    }
});

require(
[
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'application/application'
],
function($, _, Backbone, Marionette, Application) {

    // open this bad boy up to the entire window.
    window.Application = Application;

    $(function () {
        Application.start({
            userSignnedIn: true
        });
    });

});
