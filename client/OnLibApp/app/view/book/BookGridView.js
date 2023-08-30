Ext.define('OnlibApp.view.book.BookGridView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.bookgrid',
    xtype: 'bookGrid',

    title: 'Book Grid',

    store: {},
    bind: {
      store: {type: 'book'}
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
                    iconCls: 'icon-add',
                    listeners: {click:'onAddBook'}
                },
                '-',
                {
                    action: 'delete',
                    text: 'Удалить',
                    iconCls: 'icon-delete',
                    disabled: true,
                    id: 'delBookBtn',
                    listeners: {click:'onDelBook'}
                }
            ]
        }
    ],
    listeners: {cellclick: 'onLineGrid'}
});