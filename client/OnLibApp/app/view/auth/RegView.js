Ext.define('OnLibApp.view.auth.RegView', {
    extend: 'Ext.window.Window',
    xtype: 'register',

    requires: ['Ext.form.Panel'],
    controller: 'login',
    bodyPadding: 10,
    title: 'Регистрация пользователя',
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
            minLength: 3,
            minLengthText: 'Минимальная длина {0} символа',
            maxLength: 20,
            maxLengthText: 'Максимальная длина {0} символов',
            allowBlank: false,
            blankText : 'Логин не может быть пустым'
        }, {
            xtype: 'textfield',
            name: 'password',
            inputType: 'password',
            fieldLabel: 'Пароль',
            minLength: 6,
            minLengthText: 'Минимальная длина {0} символа',
            maxLength: 40,
            maxLengthText: 'Максимальная длина {0} символов',
            allowBlank: false,
            blankText : 'Пароль не может быть пустым'
        }, {
            xtype: 'textfield',
            name: 'email',
            vtype:'email',
            vtypeText: 'Значение не соответствует xxx@xxx.xx',
            maxLength: 50,
            maxLengthText: 'Максимальная длина {0} символов',
            fieldLabel: '@Email',
            allowBlank: false,
            blankText : 'E-mail не может быть пустым'
        }, {
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Имя',
            allowBlank: false,
            blankText : 'Имя не может быть пустым'
        }, {
            xtype: 'textfield',
            name: 'lastName',
            fieldLabel: 'Фамилия',
            allowBlank: false,
            blankText : 'Фамилия не может быть не заполнена'
        }],
        buttons: [{
            text: 'ОК',
            formBind: true,
            listeners: {
                click: 'onReg'
            }
        }]
    }
});