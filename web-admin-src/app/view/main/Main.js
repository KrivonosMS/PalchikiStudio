Ext.define('AdminPanel.view.main.Main', {
    extend: 'Ext.panel.Panel',

    xtype: 'app-main',

    plugins: 'viewport',

    requires: [
        'AdminPanel.view.event.MasterClass',
        'Packt.view.main.Header',
        'AdminPanel.view.main.MainModel',
        'AdminPanel.view.main.MainController'
    ],

    controller: 'main',

    viewModel: {
        type: 'main'
    },

    layout: 'border',

    items: [{
        region: 'center',
        xtype: 'masterclass'
    },{
        xtype: 'appheader',
        region: 'north'
    }]
});
