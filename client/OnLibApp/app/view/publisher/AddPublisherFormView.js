Ext.define('OnLibApp.view.publisher.AddPublisherFormView', {
    extend: 'Ext.window.Window',
    alias: 'widget.addPublisherFormView',
    controller: 'publisher-ctrl',
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
                    id: 'addPbNameField',
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
            id: 'addSavePbBtn',
            listeners: {click: 'onSavePublisher'}
        },
        {
            text: 'Отменить',
            handler: function () {
                this.up('window').close();
            }

        }
    ]
});