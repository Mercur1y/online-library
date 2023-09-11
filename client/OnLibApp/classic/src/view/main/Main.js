/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('OnLibApp.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',
    layout: 'fit',

    requires: [
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox',

        'OnLibApp.view.main.MainController',
        'OnLibApp.view.main.MainModel',
        'OnLibApp.security.Firewall'
    ],

    controller: 'main',
    viewModel: 'main',
    plugins: 'viewport',
    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretch'
        },
        title: {
            bind: {
                text: '{name}'
            },
            flex: 0
        },
        iconCls: 'x-fas fa-graduation-cap'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        },
        items: [{
            xtype: 'tbfill'

        }, {
            xtype: 'container',
            layout: {
                type: 'hbox',
                align: 'center',
                pack: 'center'
            },
            items: [{
                xtype: 'tbtext',
                text: 'some',
                style: 'font-size: 14px; font-weight: bold; color: #b9b9b9;',
                listeners: {
                    afterrender: function (item) {
                        item.setText(OnLibApp.security.Firewall.getCurrentUsername());
                    }
                }
            },{
                xtype: 'button',
                cls: 'transbtnbar',
                text: 'Выйти',
                setActive: function () {
                },
                listeners: {
                    click: function () {
                        OnLibApp.security.Firewall.logout();
                        this.up('app-main').destroy();
                        Ext.create({
                            xtype: 'login'
                        });
                    }
                }
            }]
        }]
    },

    responsiveConfig: {
        headerPosition: 'top'
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                iconAlign: 'top',
                textAlign: 'center',
                width: 120
            }
        }
    },

    listeners: {
        tabchange: 'onTabChange'
    },

    items: [
        {
            title: 'Books',
            iconCls: 'fa-book',
            id: 'books',
            items: [{xtype: 'bookGrid'}]
        }, {
            title: 'Authors',
            iconCls: 'fa-user',
            id: 'authors',
            items: [{xtype: 'authorgrid'}]
        }
    ]
});
