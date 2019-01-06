Ext.define('AdminPanel.util.Util', {
    requires: [
        'Ext.window.Toast'
    ],

    statics : {
            decodeJSON : function (text) {
            var result = Ext.JSON.decode(text, true);
            if (!result){
                result = {};
                result.success = false;
                result.msg = text;
            }
            return result;
        },

        showErrorMsg: function (text) {
            Ext.Msg.show({
                title:'Ошибка',
                msg: text,
                width: 700,
                height: 200,
                icon: Ext.Msg.ERROR,
                buttons: Ext.Msg.OK
            });
        },

        showToast: function(text) {
            Ext.toast({
                html: text,
                closable: false,
                align: 't',
                slideInDuration: 400,
                minWidth: 400
            });
        },

        handleFormFailure: function(action){
            var me = this;
            var result = me.decodeJSON(action.response.responseText);
            switch (action.failureType) {
                case Ext.form.action.Action.CLIENT_INVALID:
                me.showErrorMsg('Form fields may not be submitted with invalid values')
                break;
                case Ext.form.action.Action.CONNECT_FAILURE:
                me.showErrorMsg(action.response.responseText);
                break;
                case Ext.form.action.Action.SERVER_INVALID:
                me.showErrorMsg(result.msg);
            }
        }
    }
});