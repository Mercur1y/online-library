Ext.define('OnLibApp.controller.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLogin: function () {
        var data = this.getView().down('form').getValues();

        OnLibApp.security.Firewall.login(data.username, data.password).then(function () {
            this.getView().destroy();

            Ext.create({
                xtype: 'app-main'
            });
        }.bind(this), function (data) {
            Ext.Msg.alert('Error', data.message || 'An error occurred while logging in.');
        });
    },
    onReg: function () {
        var data = this.getView().down('form').getValues();

        OnLibApp.security.Firewall.register(
            data.username,
            data.password,
            data.email,
            data.name,
            data.lastName).then(function () {
            this.getView().destroy();

            Ext.create({
                xtype: 'login'
            });
        }.bind(this), function (data) {
            Ext.Msg.alert('Error', data.message || 'Error during registration.');
        });
    },
    onRegBtn: function () {
        this.getView().destroy();
        Ext.create({
            xtype: 'register'
        });
    }
});