Ext.define('OnLibApp.model.BookModel', {
    extend: 'Ext.data.Model',
    idProperty:'id',
    schema: {
        namespace: 'Book.model'
    },
    fields: [
        { name: 'id', type: 'int', defaultValue: 0},
        { name: 'name', type: 'string' },
        { name: 'price', type: 'float' }
    ],
});