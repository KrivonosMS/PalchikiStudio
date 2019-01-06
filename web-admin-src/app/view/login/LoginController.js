Ext.define('AdminPanel.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    requires: [
        'AdminPanel.view.main.Main',
        'AdminPanel.util.Util',
        'AdminPanel.view.login.CapsLockTooltip'
    ],

    onTextFieldSpecialKey: function(field, e, options) {
        var me = this;
        if (e.getKey() === e.ENTER) {
            me.doLogin();
        }
    },

    onTextFieldKeyPress: function(field, e, options) {
        var charCode = e.getCharCode(),
        me = this;
        if((e.shiftKey && charCode >= 97 && charCode <= 122) || (!e.shiftKey && charCode >= 65 && charCode <= 90)){
            if(me.capslockTooltip === undefined) {
                me.capslockTooltip = Ext.widget('capslocktooltip');
            }
            me.capslockTooltip.show();
            } else {
            if(me.capslockTooltip !== undefined){
                me.capslockTooltip.hide();
            }
        }
    },

    onButtonClickCancel: function(button, e, options) {
        var me = this;
        me.lookupReference('form').reset();
    },

    onButtonClickSubmit: function(button, e, options) {
        var me = this;
        if (me.lookupReference('form').isValid()){
            me.doLogin();
        }
    },

    doLogin: function() {
        var me = this;
        form = me.lookupReference('form');
        this.getView().mask('Авторизация... Пожалуйста, подождите...');
        form.submit({
            clientValidation: true,
            url: urls.login,
            scope: me,
            success: 'onLoginSuccess',
            failure: 'onLoginFailure'
        });
    },

    onLoginFailure: function(form, action) {
        var ne = this;
        this.getView().unmask();
        var result = AdminPanel.util.Util.decodeJSON(action.response.responseText);
        switch (action.failureType) {
            case Ext.form.action.Action.CLIENT_INVALID:
                AdminPanel.util.Util.showErrorMsg('Неверные данные формы');
            break;
            case Ext.form.action.Action.CONNECT_FAILURE:
                AdminPanel.util.Util.showErrorMsg(action.response.responseText);
            break;
            case Ext.form.action.Action.SERVER_INVALID:
                AdminPanel.util.Util.showErrorMsg(result.msg);
        }
    },

    onLoginSuccess: function(form, action) {
        var me = this;
        var userName = action.response.request.options.params.user;
        AdminPanel.util.AuthUtil.setIsAuth(true);
        AdminPanel.util.AuthUtil.setUserName(userName);
        me.getView().unmask();
        me.getView().destroy();
        Ext.create('AdminPanel.view.main.Main');
    }
});