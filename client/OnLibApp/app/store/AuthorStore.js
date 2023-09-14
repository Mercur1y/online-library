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
            api: {
                create: '/api/v1/author',
                read: '/api/v1/author',
                update: '/api/v1/author',
                destroy: '/api/v1/author'
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