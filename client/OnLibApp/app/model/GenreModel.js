Ext.define('OnLibApp.model.GenreModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'title', type: 'string' },
        { name: 'type', type: 'string' }
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
                create: '/api/v1/genre/create',
                read: '/api/v1/genre',
                update: '/api/v1/genre/edit',
                destroy: '/api/v1/genre/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true,
                writeRecordId: false
            }
        }
});