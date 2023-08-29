Ext.define('OnlibApp.view.book.BookGridView', {
    extend: 'Ext.grid.Panel',
    xtype: 'bookGrid',

    title: 'Book List',

    store: {
        type: 'book'
    },

    frame: true,
    controller: 'book-list',
    viewModel: {type: 'bookviewmodel'},
    reference: 'bookGrid',
    selType: 'rowmodel',
    selModel:
        {
            mode: 'SINGLE'
        },
    viewConfig:
        {
            markDirty:false
        },
    plugins: [Ext.create('Ext.grid.plugin.RowEditing',
        {
            clicksToEdit: 2,
            saveBtnText: 'Сохранить',
            cancelBtnText: 'Отменить'
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
    }, {
        text: "Price",
        flex: 1,
        dataIndex: 'price',
        editor: {allowBlank: false}
    }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            items: [
                {
                    text: 'Добавить',
                    action: 'add',
                    iconCls: 'icon-add'
                },
                '-',
                {
                    action: 'delete',
                    text: 'Удалить',
                    iconCls: 'icon-delete',
                    disabled: true
                }
            ]
        }
    ]
});