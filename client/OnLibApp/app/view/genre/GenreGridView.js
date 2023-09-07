Ext.define('OnlibApp.view.genre.GenreGridView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.genregrid',
    xtype: 'genreGrid',
    id: 'genreGridView',
    title: 'Genre Grid',

    store: {},
    bind: {
        store: {type: 'genre', pageSize: 5}
    },
    controller: 'genre-ctrl',
    viewModel: {type: 'genreviewmodel'},
    reference: 'genreGrid',
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
        text: "Title",
        flex: 1,
        dataIndex: 'title',
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
                        Ext.widget('addGenreFormView');
                    }
                },
                '-',
                {
                    action: 'delete',
                    id: 'delGenreBtn',
                    text: 'Удалить',
                    iconCls: 'icon-delete',
                    disabled: true,
                    listeners: {click: 'onDelGenre'}
                },
                '-',
                {
                    xtype: 'textfield',
                    listeners: {
                        change: function () {
                            searchByTitle(this.getValue());
                        }
                    }
                }, {
                    xtype: 'button',
                    text: 'Очистить',
                    handler: function () {
                        this.up().down('textfield').setValue('');
                        Ext.getCmp('genreGridView').store.clearFilter();
                    }
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

function searchByTitle(title) {
    var store = Ext.getCmp('genreGridView').store;
    store.clearFilter();
    store.filter('title', title);
}