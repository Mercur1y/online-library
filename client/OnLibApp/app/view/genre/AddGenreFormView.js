Ext.define('OnLibApp.view.publisher.AddGenreFormView', {
    extend: 'Ext.window.Window',
    alias: 'widget.addGenreFormView',
    controller: 'genre-ctrl',
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
                    name: 'title',
                    id: 'addGnTitleField',
                    fieldLabel: 'Наименование',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено',
                }
            ]
        }
    ],

    buttons: [
        {
            text: 'Сохранить',
            action: 'save',
            id: 'addSaveGnBtn',
            listeners: {click: 'onSaveGenre'}
        },
        {
            text: 'Отменить',
            handler: function () {
                this.up('window').close();
            }

        }
    ]
});