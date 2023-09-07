Ext.define('OnLibApp.model.PublisherModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'name', type: 'string' }
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
                create: '/api/v1/publisher/create',
                read: '/api/v1/publisher',
                update: '/api/v1/publisher/edit',
                destroy: '/api/v1/publisher/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true,
                writeRecordId: false
            }
        }
});