Ext.define('AdminPanel.Application', {
    extend: 'Ext.app.Application',

    requires: [
        'AdminPanel.view.main.Main'
    ],

    name: 'AdminPanel',

    init: function() {
        var me = this;
        me.splashscreen = Ext.getBody().mask(
            'Загрузка...', 'splashscreen'
        );
        me.splashscreen.addCls('splashscreen');
        Ext.DomHelper.insertFirst(Ext.query('.x-mask-msg')[0], {
            cls: 'x-splash-icon'
        });
    },

    launch: function() {
        Ext.tip.QuickTipManager.init();
        var me = this;
        Ext.widget('app-main');
        var task = new Ext.util.DelayedTask(function() {
            me.splashscreen.fadeOut({
                duration: 1000,
                remove: true
            });
            me.splashscreen.next().fadeOut({
                duration: 1000,
                remove: false
            });
        });
        task.delay(1000);
    }
});




