Ext.define('OnLibApp.view.book.AddBookFormView', {
    extend: 'Ext.window.Window',
    alias: 'widget.addBookFormView',
    controller: 'book-list',
    autoShow: true,
    layout: 'fit',
    modal: true,
    init: function () {'onValidation'},
    items: [
        {
            bodyPadding: 10,
            xtype: 'form',
            items: [
                {
                    xtype: 'textfield',
                    name: 'name',
                    id: 'addNameField',
                    fieldLabel: 'Название',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                    listeners: {change: 'onValidation'}
                },
                {
                    xtype: 'textfield',
                    name: 'price',
                    id: 'addPriceField',
                    fieldLabel: 'Цена',
                    regex: /^([0-9]{1,20})*$/,
                    regexText: 'Цена должна состоять из цифр',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                    listeners: {change: 'onValidation'}
                }
            ]
        }
    ],

    buttons: [
        {
            text: 'Сохранить',
            action: 'save',
            id: 'addSaveBtn',
            disabled: true,
            listeners: {click: 'onSaveBook'}
        },
        {
            text: 'Отменить',
            handler: function () {
                this.up('window').close();
            }

        }
    ]
});