Ext.define('OnLibApp.model.AuthorModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id', type: 'int'},
        { name: 'fio', type: 'string' },
        { name: 'description', type: 'string' }
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
                create: '/api/v1/author/create',
                read: '/api/v1/author',
                update: '/api/v1/author/edit',
                destroy: '/api/v1/author/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true,
                writeRecordId: false
            }
        }
});