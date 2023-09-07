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
            items: [{
                xtype: 'textfield',
                name: 'name',
                width: 500,
                id: 'addNameField',
                fieldLabel: 'Название',
                allowBlank: false,
                blankText: 'Это поле должно быть заполнено',
                listeners: {change: 'onValidation'}
            }, {
                xtype: 'textarea',
                name: 'description',
                width: 500,
                fieldLabel: 'Описание',
                blankText: 'Это поле должно быть заполнено',
                allowBlank: false
            }, {
                xtype: 'textfield',
                name: 'price',
                width: 500,
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
                width: 500,
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
                    width: 468,
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
                    id: 'publisherId',
                    width: 468,
                    xtype: 'combobox',
                    fieldLabel: 'Издательство',
                    anyMatch: true,
                    allowBlank: true,
                    editable: true,
                    typeAhead: true,
                    transform: 'stateSelect',
                    forceSelection: true,
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'id',
                    selectOnFocus: true,
                    triggerAction: 'all',
                    bind: {store: {type: 'publisher'}}
                }, {
                    xtype: 'button',
                    iconCls: 'x-fa fa-solid fa-plus',
                    handler: function () {
                        Ext.widget('addPublisherFormView');
                    }
                }]
            },{
                name: 'publishYear',
                width: 500,
                format: 'Y',
                fieldLabel: 'Год выпуска издания: ',
                xtype: 'onlyyearpicker'
            },{
                layout: 'column',
                items: [{
                    xtype: 'filefield',
                    name: 'file',
                    width: 500,
                    id: 'uploadcontentfield',
                    fieldLabel: 'Выберите файл: ',
                    buttonText: 'Выбрать...',
                    msgTarget: 'side',
                    allowBlank: false,
                    listeners: {
                        change: function (fld, value) {
                            var newValue = value.replace(/C:\\fakepath\\/g, '');
                            fld.setRawValue(newValue);
                        }
                    }
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