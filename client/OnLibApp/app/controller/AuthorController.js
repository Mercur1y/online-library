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
    onValidation: function () {
        if (Ext.getCmp('addFioField').validate() && Ext.getCmp('addDescField').validate()) {
            Ext.getCmp('addSaveAuthorBtn').enable();
        } else {
            Ext.getCmp('addSaveAuthorBtn').disable();
        }
    },
});