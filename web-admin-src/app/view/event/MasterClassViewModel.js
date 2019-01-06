Ext.define('AdminPanel.view.event.MasterClassViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.master-class',

    stores: {
        masterClass: {
            model: 'AdminPanel.model.MasterClassModel',
            pageSize: 25,
            autoLoad: true
        }
    }
});