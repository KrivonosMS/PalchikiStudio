Ext.define('AdminPanel.util.AuthUtil', {
    statics : {
        IS_AUTH: 'isAuth',
        USER_NAME: 'userName',

        setIsAuth: function(isAuth) {
            var me = this;
            localStorage.setItem(me.IS_AUTH, isAuth);
        },

        setUserName: function(userName) {
            var me = this;
            localStorage.setItem(me.USER_NAME, userName);
        },

        isAuth: function() {
            var me = this;
            return localStorage.getItem(me.IS_AUTH);
        },

        getUserName: function() {
            var me = this;
            return localStorage.getItem(me.USER_NAME);
        }
    }
});