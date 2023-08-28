Ext.define('OnlibApp.view.book.BookList', {
    extend: 'Ext.grid.Panel',
    xtype: 'bookList',

    requires: ['OnLibApp.store.BookStore'],

    title: 'Book List',

    store: {
        type: 'book'
    },

    controller: 'book-list',
    viewModel: {type: 'bookviewmodel'},
    reference: 'booklistgrid',
    selType: 'rowmodel',
    selModel:
        {
            mode: 'SINGLE'
        },
    viewConfig:
        {
            stripeRows: true
        },
    listeners: {
        selectionchange: 'onSelectionChange'
    },
    initComponent: function () {
        Ext.apply(this,
            {
                plugins: [Ext.create('Ext.grid.plugin.RowEditing',
                    {
                        clicksToEdit: 2
                    })],

                columns: [{
                    text: "Id",
                    dataIndex: 'Id',
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
                tbar: [{
                    text: 'Add Book',
                    iconCls: 'fa-plus',
                    handler: 'onAddClick'
                }, {
                    itemId: 'removeBook',
                    text: 'Remove Book',
                    iconCls: 'fa-times',
                    reference: 'btnRemoveBook',
                    handler: 'onRemoveClick',
                    disabled: true
                }]
            });

        this.callParent(arguments);
    }
});