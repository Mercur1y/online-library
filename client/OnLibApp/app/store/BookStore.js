Ext.define('OnLibApp.store.BookStore', {
    extend: 'Ext.data.Store',
    storeId: 'bookstoreid',
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
                    rootProperty: 'content',
                    totalProperty: 'totalElements',
                    type: 'json'
                },
            api: {
                create: '/api/v1/book',
                read: '/api/v1/book',
                update: '/api/v1/book',
                destroy: '/api/v1/book'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true,
                writeRecordId: false,
                allDataOptions: {
                    associated: true,
                    persist: true
                }
            }
        }
});