Ext.define('OnLibApp.view.author.AddAuthorFormView', {
    extend: 'Ext.window.Window',
    alias: 'widget.addAuthorFormView',
    controller: 'author-ctrl',
    autoShow: true,
    layout: 'fit',
    modal: true,
    items: [
        {
            bodyPadding: 10,
            xtype: 'form',
            items: [
                {
                    xtype: 'textfield',
                    name: 'fio',
                    id: 'addFioField',
                    fieldLabel: 'ФИО',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                    listeners: {change: 'onValidation'}
                }, {
                    xtype: 'textfield',
                    name: 'description',
                    id: 'addDescField',
                    fieldLabel: 'Описание',
                },
                    ]
                }
            ],

    buttons: [
        {
            text: 'Сохранить',
            action: 'save',
            id: 'addSaveAuthorBtn',
            disabled: true,
            listeners: {click: 'onSaveAuthor'}
        },
        {
            text: 'Отменить',
            handler: function () {
                this.up('window').close();
            }

        }
    ]
});