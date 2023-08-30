Ext.define('OnLibApp.model.GenreModel', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id', type: 'int' },
        { name: 'type', type: 'string' },
        { name: 'title', type: 'string' }
    ],
});