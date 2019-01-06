Ext.define('AdminPanel.model.MasterClassModel', {
    extend: 'Ext.data.Model',

    fields: ['master_class_id', 'name', 'teacher_name', 'description', 'date', 'coast', 'img_path'],

    idProperty : 'master_class_id',

    proxy: {
        type: 'ajax',
        url: urls.master_class,
        api: {
            destroy: urls.delete
        },
        reader: {
            type: 'json',
            rootProperty: 'data',
            successProperty: 'success'
        }
    }
});