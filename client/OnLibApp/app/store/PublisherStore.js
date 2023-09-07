Ext.define('OnLibApp.store.PublisherStore', {
    extend: 'Ext.data.Store',
    storeId: 'publisherstoreid',
    alias: 'store.publisher',
    pageSize: 5,
    model: 'OnLibApp.model.PublisherModel',
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
            url: '/api/v1/publisher',
            api: {
                create: '/api/v1/publisher/create',
                read: '/api/v1/publisher',
                update: '/api/v1/publisher/edit',
                destroy: '/api/v1/publisher/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});