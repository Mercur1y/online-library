Ext.define('OnLibApp.store.BookStore', {
    extend: 'Ext.data.Store',

    alias: 'store.book',
    model: 'OnLibApp.model.BookModel',
    autoLoad: true,
    autoSync: true,
    proxy:
        {
            headers: {
                'Content-type': 'application/json'
            },
            type: 'rest',
            reader:
                {
                    rootProperty: 'data',
                    type: 'json'
                },
            url: '/api/v1/book',
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});