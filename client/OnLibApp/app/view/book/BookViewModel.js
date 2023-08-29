Ext.define('OnlibApp.view.book.BookViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.bookviewmodel',
    stores: {
        bookStore: {
            type: 'book'
        }
    }
});