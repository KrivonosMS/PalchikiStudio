Ext.define('AdminPanel.view.login.CapsLockTooltip', {
    extend: 'Ext.tip.ToolTip',
    xtype: 'capslocktooltip',

    target: 'password',

    anchor: 'top',

    anchorOffset: 0,

    width: 300,

    dismissDelay: 0,

    autoHide: false,

    title: '<div class="fa fa-exclamation-triangle">Caps Lock</div>',

    html: 'Внимание! Включен Caps Lock.'
});