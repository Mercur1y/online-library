/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('OnLibApp.Application', {
    extend: 'Ext.app.Application',
    
    name: 'OnLibApp',

    required: ['OnLibApp.security.Firewall'],

    stores: [
    ],
    
    launch: function () {
        Ext.create({
            xtype: OnLibApp.security.Firewall.isLoggedIn() ? 'app-main' : 'login'
        });
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    },

    defaultToken : 'books'
});
