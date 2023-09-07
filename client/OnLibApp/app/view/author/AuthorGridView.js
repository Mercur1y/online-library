Ext.define('OnlibApp.view.author.AuthorGridView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.authorgrid',
    xtype: 'authorGrid',

    title: 'Author Grid',

    store: {},
    bind: {
        store: {type: 'author', pageSize: 5}
    },
    controller: 'author-ctrl',
    viewModel: {type: 'authorviewmodel'},
    reference: 'authorGrid',
    selType: 'rowmodel',
    selModel: {mode: 'SINGLE'},
    viewConfig: {stripeRows: true},
    plugins: [Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToEdit: 2
    })],
    columns: [{
        text: "Id",
        dataIndex: 'id',
        width: 35
    }, {
        text: "FIO",
        flex: 1,
        dataIndex: 'fio'
    }, {
        text: "Desc",
        flex: 1,
        dataIndex: 'description',
        editor: {allowBlank: true}
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
                        Ext.widget('addAuthorFormView');
                    }
                },
                '-',
                {
                    action: 'delete',
                    text: 'Удалить',
                    iconCls: 'icon-delete',
                    disabled: true,
                    id: 'delAuthorBtn',
                    listeners: {click:'onDelAuthor'}
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