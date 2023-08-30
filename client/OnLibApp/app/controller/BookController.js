Ext.define('OnLibApp.controller.BookController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.book-list',

    refs: [
        {selector: 'bookGrid',
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

    onAddBook: function () {
        Ext.widget('addBookFormView');
    },

    onSaveBook: function (button) {
        var me = this;
        var bookModel = Ext.create('OnLibApp.model.BookModel');
        bookModel.set(this.getView().down('form').getValues());
        bookModel.save({
            success: function (operation, response) {
                Ext.StoreManager.lookup('bookstoreid').load();
                me.getView().close();
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
        var view = this.getView();
        var sel = view.getSelection();
        view.store.remove(sel);
        view.store.commitChanges();
        Ext.getCmp('delBookBtn').disable();
    },

    onLineGrid: function () {
        Ext.getCmp('delBookBtn').enable();
    },

    onValidation: function () {
        if (Ext.getCmp('addNameField').validate() && Ext.getCmp('addPriceField').validate()) {
            Ext.getCmp('addSaveBtn').enable();
        } else {
            Ext.getCmp('addSaveBtn').disable();
        }
    }
});