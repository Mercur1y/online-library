Ext.define('OnLibApp.model.AuthorModel', {
    extend: 'Ext.data.Model',
    proxy:
        {
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