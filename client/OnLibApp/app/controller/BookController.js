Ext.define('OnLibApp.controller.BookController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.book-list',

    refs: [
        {selector: 'BookList',
            ref: 'bookGridView'},
        {selector: 'bookGridView button[action="add"]',
            ref: 'bookGridAdd'},
        {selector: 'bookGridView button[action="delete"]',
            ref: 'bookGridDelete'},
        {selector: 'addBookFormView',
            ref: 'addBookFormView'},
        {selector: 'addBookFormView textfield[name=name] ',
            ref: 'addBookFormName'},
        {selector: 'addBookFormView textfield[name=price]',
            ref: 'addBookFormPrice'},
        {selector: 'addBookFormView button[action=save]',
            ref: 'addBookFormSave'}
    ],

    init: function () {
        this.control({
            'bookGridView button[action=add]': {
                click: this.onAddBook
            },
            'bookGridView button[action=delete]': {
                click: this.onDelBook
            },
            'addBookFormView button[action=save]': {
                click: this.onSaveBook
            },
            'addBookFormView textfield[name=name]': {
                change: this.onValidation
            },
            'addBookFormView textfield[name=price]': {
                change: this.onValidation
            },
            'bookGridView': {
                cellclick: this.onLineGrid
            }
        });
    },

    onAddBook: function () {
        Ext.widget('addBookFormView');
    },

    onSaveBook: function (button) {
        var me = this;
        var bookModel = Ext.create('OnLibApp.model.BookModel');
        bookModel.set(this.getAddBookFormView().down('form').getValues());
        bookModel.save({
            success: function (operation, response) {
                var objAjax = operation.data;
                Ext.getStore('BookStore').add(objAjax);
                me.getAddBookFormView().close();
            },
            failure: function (dummy, result) {
                Ext.MessageBox.show({
                    title: 'Дубликат!',
                    msg: 'Такая модель и цена уже существуют',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            }

        });
    },

    onDelBook: function () {
        var sm = this.getBookGridView().getSelectionModel();
        var rs = sm.getSelection();
        this.getBookGridView().store.remove(rs[0]);
        this.getBookGridView().store.commitChanges();
        this.getBookGridDelete().disable();
    },

    onLineGrid: function () {
        this.getBookGridDelete().enable();
    },

    onValidation: function () {
        if (this.getAddBookFormName().validate() && this.getAddBookFormPrice().validate()) {
            this.getAddBookFormSave().enable();
        } else {
            this.getAddBookFormSave().disable();
        }
    }
});