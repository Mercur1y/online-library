Ext.define('OnLibApp.model.GenreModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'title', type: 'string' }
    ],
    proxy:
        {
            type: 'rest',
            reader:
                {
                    rootProperty: 'data',
                    type: 'json'
                },
            api: {
                create: '/api/v1/genre',
                read: '/api/v1/genre',
                update: '/api/v1/genre',
                destroy: '/api/v1/genre'
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