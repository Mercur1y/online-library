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
                create: '/api/v1/publisher',
                read: '/api/v1/publisher',
                update: '/api/v1/publisher',
                destroy: '/api/v1/publisher'
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