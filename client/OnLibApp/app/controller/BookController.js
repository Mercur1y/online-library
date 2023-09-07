Ext.define('OnLibApp.controller.BookController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.book-list',

    onAddBook: function () {
        Ext.widget('addBookFormView');
    },

    onSaveBook: function (button) {
        var me = this;
        var bookModel = Ext.create('OnLibApp.model.BookModel');
        var uuid = this.uuidv4();
        bookModel.set(this.getView().down('form').getValues());
        bookModel.set('suuid', uuid);
        bookModel.save({
            success: function () {
                var file = me.getView().down('filefield').el.down('input[type=file]').dom.files[0],
                data = new FormData();
                data.append('file', file);
                data.append('uuid', JSON.stringify(uuid));
                Ext.Ajax.request({
                    url: '/api/v1/file/content',
                    rawData: data,
                    headers: {'Content-Type':null},
                    success: function(response){ }
                });
                me.getView().close();
                setTimeout(function() {Ext.StoreManager.lookup('bookstoreid').load();}, 800);
            },
            failure: function () {
                Ext.MessageBox.show({
                    title: 'Дубликат!',
                    msg: 'Такая модель и цена уже существуют',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            }});
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
    },

    uuidv4: function () {
    return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );}
});