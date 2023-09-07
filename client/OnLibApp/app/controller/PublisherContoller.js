Ext.define('OnLibApp.controller.PublisherController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.publisher-ctrl',

    onSavePublisher: function () {
        var me = this;
        var pbModel = Ext.create('OnLibApp.model.PublisherModel');
        pbModel.set(this.getView().down('form').getValues());
        pbModel.save({
            success: function () {
                Ext.StoreManager.lookup('publisherstoreid').load();
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
        Ext.getCmp('delPbBtn').enable();
    },

    onDelPublisher: function () {
        var view = this.getView();
        var sel = view.getSelection();
        view.store.remove(sel);
        view.store.commitChanges();
        Ext.getCmp('delPbBtn').disable();
    },
});