Ext.define('OnLibApp.model.BookModel', {

    extend: 'Ext.data.Model',
    idProperty:'Id',
    schema: {
        namespace: 'Book.model'
    },
    fields: [
        { name: 'Id', type: 'int', defaultValue: 0},
        { name: 'name', type: 'string' },
        { name: 'price', type: 'double' }
    ],
});