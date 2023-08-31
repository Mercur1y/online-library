Ext.define('OnLibApp.store.AuthorStore', {
    extend: 'Ext.data.Store',
    storeId: 'authorstoreid',
    alias: 'store.author',
    model: 'OnLibApp.model.AuthorModel',
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
            url: '/api/v1/author/',
            api: {
                create: '/api/v1/author/create',
                read: '/api/v1/author',
                update: '/api/v1/author/edit',
                destroy: '/api/v1/author/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});