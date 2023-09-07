Ext.define('OnlibApp.view.book.BookGridView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.bookgrid',
    xtype: 'bookGrid',

    title: 'Book Grid',

    store: {},
    bind: {
        store: {type: 'book', pageSize: 10}
    },

    frame: true,
    controller: 'book-list',
    viewModel: {type: 'bookviewmodel'},
    reference: 'bookGrid',
    selType: 'rowmodel',
    selModel: {mode: 'SINGLE'},
    viewConfig: {stripeRows: true},
    plugins: [Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToEdit: 2
    })],
    columns: [{
        text: "Id", dataIndex: 'id', hidden: false, width: 35
    }, {
        text: "Name", flex: 1, dataIndex: 'name',
    }, {
        text: "Desc", flex: 1, dataIndex: 'description', editor: {allowBlank: false}
    }, {
        text: "Price", flex: 1, dataIndex: 'price', editor: {allowBlank: false}
    }, {
        text: "Genre", id: 'genres', flex: 1, dataIndex: 'genres', renderer: function (v) {
            return v.map(function (item) {
                return item.title
            }).join(', ');
        }
    }, {
        text: "Author", id: 'author', flex: 1, dataIndex: 'author', renderer: function (item) {
            return item.fio
        }
    }, {
        xtype: 'widgetcolumn', widget: {
            xtype: 'button', text: "Читать...", defaultBindProperty: null, //important
            listeners: {
                click: function (widgetColumn) {
                    var record = widgetColumn.getWidgetRecord(), filename = record.get('file').name,
                        author = record.get('author').fio, bookName = record.get('name');
                    sessionStorage.setItem("path", filename);

                    var bookRead = Ext.widget('bookread')
                    bookRead.setTitle(author + ' - ' + bookName);
                }
            }
        }
    }],
    dockedItems: [{
        xtype: 'toolbar', items: [{
            text: 'Добавить', action: 'add', iconCls: 'icon-add', listeners: {click: 'onAddBook'}
        }, '-', {
            action: 'delete',
            text: 'Удалить',
            iconCls: 'icon-delete',
            disabled: true,
            id: 'delBookBtn',
            listeners: {click: 'onDelBook'}
        }]
    }, {
        xtype: 'pagingtoolbar',
        store: this.store,
        dock: 'bottom',
        displayInfo: true,
        beforePageText: 'Страница',
        afterPageText: 'из {0}',
        displayMsg: '{0} - {1} из {2}'
    }],
    listeners: {cellclick: 'onLineGrid'}
});