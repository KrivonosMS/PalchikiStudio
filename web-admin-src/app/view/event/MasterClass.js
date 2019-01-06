Ext.define('AdminPanel.view.event.MasterClass' ,{
    extend: 'Ext.panel.Panel',
    xtype: 'masterclass',

    requires: [
        'AdminPanel.view.event.MasterClassGrid',
        'AdminPanel.view.event.MasterClassController'
    ],

    controller: 'master-class',

    viewModel: {
        type: 'master-class'
    },

    layout: 'fit',

    items: [{
        xtype: 'masterclassgrid'
    }],

    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            xtype: 'button',
            text: 'Добавить',
            reference: 'onAddButton',
            glyph: 'xf067@FontAwesome',
            listeners: {
                click: 'onAdd'
            }
        }]
    }]
});