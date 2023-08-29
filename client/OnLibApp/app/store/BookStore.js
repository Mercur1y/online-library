Ext.define('OnLibApp.store.BookStore', {
    extend: 'Ext.data.Store',

    alias: 'store.book',
    model: 'OnLibApp.model.BookModel',
    autoLoad: true,
    autoSync: true,
    proxy:
        {
            headers: {
                'Accept': 'application/json'
            },
            type: 'rest',
            reader:
                {
                    rootProperty: 'data',
                    type: 'json'
                },
            url: '/api/v1/book',
            api: {
                create: '/api/v1/book/create',
                read: '/api/v1/book',
                update: '/api/v1/book/edit',
                destroy: '/api/v1/book/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});