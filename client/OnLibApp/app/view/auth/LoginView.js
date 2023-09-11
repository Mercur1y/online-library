Ext.define('OnLibApp.view.auth.LoginView', {
    extend: 'Ext.window.Window',
    xtype: 'login',

    requires: ['Ext.form.Panel'],
    controller: 'login',
    bodyPadding: 10,
    title: 'OnLib',
    titleAlign: 'center',
    closable: false,
    autoShow: true,

    items: {
        xtype: 'form',
        reference: 'form',
        items: [{
            xtype: 'textfield',
            name: 'username',
            fieldLabel: 'Логин',
            allowBlank: false,
            blankText : 'Пожалуйста, введите логин'
        }, {
            xtype: 'textfield',
            name: 'password',
            inputType: 'password',
            fieldLabel: 'Пароль',
            allowBlank: false,
            blankText : 'Пожалуйста, введите пароль'
        }],
        buttons: [{
            cls: 'transbtn',
            text: 'Регистрация',
            listeners: {
                click: 'onRegBtn'
            }
        }, {
            text: 'Войти',
            formBind: true,
            listeners: {
                click: 'onLogin'
            }
        }]
    }
});