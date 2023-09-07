Ext.define('OnlibApp.view.publisher.PublisherGridView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.publishergrid',
    xtype: 'publisherGrid',
    title: 'Publisher Grid',

    store: {},
    bind: {
        store: {type: 'publisher', pageSize:5}
    },
    controller: 'publisher-ctrl',
    viewModel: {type: 'publisherviewmodel'},
    reference: 'publisherGrid',
    selType: 'rowmodel',
    selModel: {mode: 'SINGLE'},
    viewConfig: {stripeRows: true},
    plugins: [Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToEdit: 2
    })],
    columns: [{
        text: "Id",
        dataIndex: 'id',
        hidden: false,
        width: 35
    }, {
        text: "Name",
        flex: 1,
        dataIndex: 'name',
        editor: {allowBlank: false}
    }],
    dockedItems: [
        {
            xtype: 'toolbar',
            items: [
                {
                    text: 'Добавить',
                    action: 'add',
                    iconCls: 'icon-add',
                    handler: function () {
                        Ext.widget('addPublisherFormView');
                    }
                },
                '-',
                {
                    action: 'delete',
                    id: 'delPbBtn',
                    text: 'Удалить',
                    iconCls: 'icon-delete',
                    disabled: true,
                    listeners: {click:'onDelPublisher'}
                }
            ]
        }, {
            xtype: 'pagingtoolbar',
            store: this.store,
            dock: 'bottom',
            displayInfo: true,
            beforePageText: 'Страница',
            afterPageText: 'из {0}',
            displayMsg: '{0} - {1} из {2}'
        }
    ],
    listeners: {cellclick: 'onLineGrid'}
});