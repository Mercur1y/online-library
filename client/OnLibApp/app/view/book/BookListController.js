Ext.define('OnLibApp.view.book.BookListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.book-list',

    onAddClick: function (sender, record) {
        var bookStore = this.getViewModel().getStore('BookStore');

        var bookModel = Ext.create('OnLibApp.model.BookModel');
        bookModel.set("Id", 0);
        bookModel.set("name", "New Book");
        bookModel.set("price", "200.56");
        bookStore.insert(0, bookModel);

        var bookGrid = this.getView();
        bookGrid.editingPlugin.startEdit(bookModel, 1);
    },

    onLoadClick: function (sender, record) {
        var bookStore = this.getView().getStore();
        bookStore.load();
    },

    onRemoveClick: function (sender, record) {
        var bookGrid = this.getView();
        var bookStore = bookGrid.getStore();

        //delete selected rows if selModel is checkboxmodel
        var selectedRows = bookGrid.getSelectionModel().getSelection();

        bookStore.remove(selectedRows);
    },

    onSelectionChange: function (sender, record, isSelected) {
        var removeBtn = this.lookupReference('btnRemoveStudent');
        if(record.length)
            removeBtn.setDisabled(false);
        else
            removeBtn.setDisabled(true);
    }
});