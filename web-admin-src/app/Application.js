Ext.define('AdminPanel.Application', {
    extend: 'Ext.app.Application',

    requires: [
        'AdminPanel.view.login.Login',
        'AdminPanel.util.AuthUtil',
        'AdminPanel.view.main.Main',
        'AdminPanel.util.AuthUtil'
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
        if (AdminPanel.util.AuthUtil.isAuth() === 'true') {
            Ext.Ajax.on({
                requestexception: me.onRequestException,
                scope: me
            });
            Ext.widget('app-main');
        } else {
            Ext.widget('login-dialog');
        }
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
    },

    onRequestException: function(conn, response) {
        if (response.status === 401) {
            AdminPanel.util.AuthUtil.setIsAuth(false);
            AdminPanel.util.AuthUtil.setUserName('');
            window.location.reload();
        } else {
            var result = AdminPanel.util.Util.decodeJSON(response.responseText)
            AdminPanel.util.Util.showErrorMsg(result.msg);
        }
    }
});




