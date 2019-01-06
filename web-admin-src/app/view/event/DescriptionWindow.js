Ext.define('AdminPanel.view.event.DescriptionWindow', {
    extend: 'Ext.window.Window',
    xtype: 'description-window',

    height: 400,

    width: 500,

    layout: {
        type: 'fit'
    },

    modal: true,

    autoShow: true,

    initComponent: function() {
        var me = this;
        me.items = [{
            xtype: 'textareafield',
            value: me.description
        }],
        me.callParent();
    }
});