Ext.define('AdminPanel.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    requires: [
        'AdminPanel.util.Util',
        'AdminPanel.util.AuthUtil'
    ],

    onLogout: function(button, e, options){
        var me = this;
        Ext.Ajax.request({
            url: urls.logout,
            scope: me,
            success: 'onLogoutSuccess',
            failure: 'onLogoutFailure'
        });
    },

    onLogoutSuccess: function(conn, response, options, eOpts){
        var result = AdminPanel.util.Util.decodeJSON(conn.responseText);
        if (result.success) {
            AdminPanel.util.AuthUtil.setIsAuth(false);
            AdminPanel.util.AuthUtil.setUserName('');
            window.location.reload();
        } else {
            AdminPanel.util.Util.showErrorMsg(result.msg);
        }
    },

    onLogoutFailure: function(conn, response, options, eOpts){
        AdminPanel.util.Util.showErrorMsg(response.responseText);
    }
});
