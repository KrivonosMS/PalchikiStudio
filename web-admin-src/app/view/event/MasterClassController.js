Ext.define('AdminPanel.view.event.MasterClassController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.master-class',

    requires: [
        'AdminPanel.util.Util',
        'AdminPanel.view.event.DescriptionWindow'
    ],

    onCancel: function(button, e, options) {
        var me = this;
        me.dialog = Ext.destroy(me.dialog);
        me.setAddingButtonDisabled(false);
    },

    onAdd: function() {
        var me  = this;
        me.createDialog(null);
        me.setAddingButtonDisabled(true);
    },

    setAddingButtonDisabled: function(isDisabled) {
        var me = this;
        var button = me.getView().lookupReference('onAddButton');
        button.setDisabled(isDisabled);
    },

    onEdit: function(grid, rowIndex, colIndex, item, event, record){
        var me = this;
        me.createDialog(record);
        me.setAddingButtonDisabled(true);
    },

    createDialog: function(record){
        var me = this;
        view = me.getView();
        me.dialog = view.add({
            xtype: 'master-class-form',
            viewModel: {
                data: {
                    title: record ? 'Редактировать: ' + record.get('name') : 'Добавить мастер-класс',
                    currentMasterClass: record ? record.data : {
                      type: 'AdminPanel.model.MasterClassModel',
                      create: true
                    }
                }
            }
        });
        me.dialog.show();
    },

    onDelete: function(grid, rowIndex, colIndex, item, event, record) {
        var me = this;
        view = me.getView();
        store = me.getStore('masterClass');
        Ext.Msg.show({
            title:'Удалить?',
            msg: 'Вы, действительно, хотите удалить мастер-класс?',
            height: 120,
            width: 400,
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function (buttonId){
                if (buttonId == 'yes'){
                    store.remove(record);
                    store.sync();
                    me.setAddingButtonDisabled(false);
                }
            }
        });
    },

    onSave: function(button, e, options){
        var me = this,
        form = me.lookupReference('form');
        if (form && form.isValid()) {
            form.submit({
                clientValidation: true,
                url: urls.save,
                scope: me,
                success: 'onSaveSuccess',
                failure: 'onSaveFailure'
            });
        }
    },

    onSaveSuccess: function(form, action) {
        var me = this;
        var result = AdminPanel.util.Util.decodeJSON(action.response.responseText);
        if (result.success) {
            me.onCancel();
            me.refresh();
            me.setAddingButtonDisabled(false);
            AdminPanel.util.Util.showToast('Мастер-класс сохранен.');
        } else {
            AdminPanel.util.Util.showErrorMsg(result.msg);
        }
    },

    refresh: function(button, e, options){
        var me = this;
        var store = me.getStore('masterClass');
        store.load();
    },

    onSaveFailure: function(form, action) {
        AdminPanel.util.Util.handleFormFailure(action);
    },

    onFileFieldChange: function(fileField, value, options) {
        var me = this;
        var file = fileField.fileInputEl.dom.files[0];
        var picture = this.lookupReference('masterClassPicture');
        if (typeof FileReader !== 'undefined' && (/image/i).test(file.type)) {
            var reader = new FileReader();
            reader.onload = function(e) {
                picture.setSrc(e.target.result);
            };
            reader.readAsDataURL(file);
        } else if (!(/image/i).test(file.type)) {
            Ext.Msg.alert('Внимание', 'Можно загружать только изображения!');
            fileField.reset();
        }
    },

    showDescription: function(view, td, cellIndex, record, tr, rowIndex, e, eOpts) {
        var me = this;
        var grid = me.lookupReference('masterclassgrid');

        if (grid.columns[cellIndex].dataIndex === 'description') {
            var description = record.data.description;
            var descWindow = Ext.create('widget.description-window', {description: description});
        }
    }
});