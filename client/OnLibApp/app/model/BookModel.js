Ext.define('OnLibApp.model.BookModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'price', type: 'float'},
        {name: 'description', type: 'string'},
        {name: 'price', type: 'number'},
        {name: 'authorId',
            type: 'int',
            reference: 'OnLibApp.model.AuthorModel'
        }
    ],
    hasMany: [
        {
            model: 'OnLibApp.model.GenreModel',
            name: 'genres',
            associationKey: 'genres'
        },
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
                create: '/api/v1/book',
                read: '/api/v1/book',
                update: '/api/v1/book',
                destroy: '/api/v1/book'
            },
            writer: {
                type: 'json',
                dateFormat: 'd/m/Y',
                writeAllFields: true,
                writeRecordId: false,
                allDataOptions: {
                    associated: true,
                    persist: true
                },
            }
        }
});