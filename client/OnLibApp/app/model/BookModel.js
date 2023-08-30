Ext.define('OnLibApp.model.BookModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id', type: 'int', persist: false},
        { name: 'name', type: 'string' },
        { name: 'price', type: 'float' }
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
                create: '/api/v1/book/create',
                read: '/api/v1/book',
                update: '/api/v1/book/edit',
                destroy: '/api/v1/book/delete'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true
            }
        }
});