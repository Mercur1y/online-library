Ext.define('OnLibApp.view.main.RowGrid', {
    extend: 'Ext.panel.Panel',
    requires: [
        'OnlibApp.view.author.AuthorGridView',
        // 'OnlibApp.view.genre.GenreGridView',
    ],
    xtype: 'rowGrid',
    items: [{
        xtype: 'bookGrid'
    }, {
        id: 'gAndP',
        layout: {
            type: 'table',
            columns: 3,
            tableAttrs: {
                    width: '100%',
            }
        },
        defaults: {
            border: true
        },
        items: [{
            cls: 'tColBody',
            xtype: 'authorGrid',
        },/*{
            cls: 'tColBody',
            xtype: 'genreGrid',
        },*/ {
            cls: 'tColBody',
            xtype: 'publisherGrid'
        }]
    }]
});