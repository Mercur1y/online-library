/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('OnLibApp.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    },

    routes : {
        'books' : 'onBooks',
        'authors': 'onAuthors'
    },

    onBooks: function () {
        this.onRouteChange('books')
    },

    onAuthors: function () {
        this.onRouteChange('authors')
    },

    onRouteChange: function (route) {
        this.getView().setActiveItem(route);
    },

    onTabChange: function (panel, newTab) {
        this.changeTab(newTab.id);
    },

    changeTab: function (id) {
        this.redirectTo(id);
    }
});
