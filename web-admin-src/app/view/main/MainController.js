Ext.define('AdminPanel.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    onLogout: function(button, e, options){
        var me = this;
        window.location = '/PalchikiStudio/logout';
    }
});
