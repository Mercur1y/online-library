Ext.define('OnLibApp.view.book.AddBookFormView', {
    extend: 'Ext.window.Window',
    alias: 'widget.addBookFormView',
    controller: 'book-list',
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
                    name: 'name',
                    id: 'addNameField',
                    fieldLabel: 'Название',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                    listeners: {change: 'onValidation'}
                }, {
                    xtype: 'textfield',
                    name: 'price',
                    id: 'addPriceField',
                    fieldLabel: 'Цена',
                    regex: /^-?[0-9]+([.,][0-9]+)?$/,
                    regexText: 'Цена должна состоять из цифр',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                    listeners: {change: 'onValidation'}
                }, {
                    xtype: 'tagfield',
                    fieldLabel: 'Жанр',
                    id: 'genreIds',
                    queryMode: 'local',
                    bind: {store: {type: 'genre'}},
                    valueField: 'id',
                    displayField: 'title',
                    renderTo: Ext.getBody(),
                    multiSelect: true
                }, {
                    layout: 'column',
                    items: [{
                        id: 'authorId',
                        xtype: 'combobox',
                        fieldLabel: 'Автор',
                        anyMatch: true,
                        allowBlank: true,
                        editable: true,
                        typeAhead: true,
                        transform: 'stateSelect',
                        forceSelection: true,
                        queryMode: 'local',
                        displayField: 'fio',
                        valueField: 'id',
                        selectOnFocus: true,
                        triggerAction: 'all',
                        bind: {store: {type: 'author'}}
                    }, {
                        xtype: 'button',
                        iconCls: 'x-fa fa-solid fa-plus',
                        handler: function () {
                            Ext.widget('addAuthorFormView');
                        }
                    }]
                }, {
                    layout: 'column',
                    items: [{
                        xtype: 'filefield',
                        name: 'file',
                        id: 'uploadcontentfield',
                        fieldLabel: 'Выберите файл: ',
                        msgTarget: 'side',
                        allowBlank: false,
                    }]
                }]
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