Ext.define('OnLibApp.controller.AuthorController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.author-ctrl',

    onSaveAuthor: function () {
        var me = this;
        var authorModel = Ext.create('OnLibApp.model.AuthorModel');
        authorModel.set(this.getView().down('form').getValues());
        authorModel.save({
            success: function () {
                Ext.StoreManager.lookup('authorstoreid').load();
                me.getView().close();
            },
            failure: function () {
                Ext.MessageBox.show({
                    title: 'Error',
                    msg: 'Error',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            }
        });
    },
    onLineGrid: function () {
        Ext.getCmp('delAuthorBtn').enable();
    },

    onDelAuthor: function () {
        var view = this.getView();
        var sel = view.getSelection();
        if(view.getSelection()[0].getData().books.length !== 0) {
            Ext.Msg.confirm('Вы уверены?',
                'У автора есть книги. Если вы удалите автора, книги удаляться',
                function (choice) {
                    if (choice === 'yes') {
                        view.store.remove(sel);
                        view.store.commitChanges();
                        Ext.StoreManager.lookup('bookstoreid').load();
                    } else Ext.getCmp('delAuthorBtn').enable();
                }, this);
        } else {
            view.store.remove(sel);
            view.store.commitChanges();
        }
        Ext.getCmp('delAuthorBtn').disable();
    },

    onValidation: function () {
        if (Ext.getCmp('addFioField').validate() && Ext.getCmp('addDescField').validate()) {
            Ext.getCmp('addSaveAuthorBtn').enable();
        } else {
            Ext.getCmp('addSaveAuthorBtn').disable();
        }
    },
});