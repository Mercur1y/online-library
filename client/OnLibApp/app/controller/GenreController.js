Ext.define('OnLibApp.controller.GenreController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.genre-ctrl',

    onSaveGenre: function () {
        var me = this;
        var genreModel = Ext.create('OnLibApp.model.GenreModel');
        genreModel.set(this.getView().down('form').getValues());
        genreModel.save({
            success: function () {
                Ext.StoreManager.lookup('genrestoreid').load();
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
        Ext.getCmp('delGenreBtn').enable();
    },

    onDelGenre: function () {
        var view = this.getView();
        var sel = view.getSelection();
        view.store.remove(sel);
        view.store.commitChanges();
        Ext.getCmp('delGenreBtn').disable();
    },
});