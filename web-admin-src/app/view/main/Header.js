Ext.define('Packt.view.main.Header', {
    extend: 'Ext.toolbar.Toolbar',

    xtype: 'appheader',

    items: [{
        xtype: 'component',
        componentCls: 'app-header-title',
        bind: {
            html: '{appName}'
        }
    },{
        xtype: 'tbfill'
    },{
        xtype: 'button',
        itemId: 'logout',
        text: 'Logout',
        reference: 'logout',
        iconCls: 'fa fa-sign-out fa-lg buttonIcon',
        listeners: {
            click: 'onLogout'
        }
    }]
});