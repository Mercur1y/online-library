Ext.define('OnLibApp.store.GenreStore', {
    extend: 'Ext.data.Store',
    storeId: 'genrestoreid',
    alias: 'store.genre',
    model: 'OnLibApp.model.GenreModel',
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
            url: '/api/v1/genre',
            api: {
                create: '/api/v1/genre/create',
                read: '/api/v1/genre',
                update: '/api/v1/genre/edit',
                destroy: '/api/v1/genre/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});