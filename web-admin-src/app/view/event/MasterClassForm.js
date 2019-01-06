Ext.define('AdminPanel.view.event.MasterClassForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.master-class-form',

    height: 330,

    width: 900,

    requires: [
        'AdminPanel.util.Util'
    ],

    layout: {
        type: 'fit'
    },

    bind: {
        title: '{title}'
    },

    closable: false,

    modal: true,

    items: [{
        xtype: 'form',
        reference: 'form',
        bodyPadding: 5,
        modelValidation: true,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        defaults: {
            margin: 5,
        },
        items: [{
            xtype: 'fieldset',
            flex: 1,
            title: 'Мастре-класс',
            layout: 'anchor',
            defaults: {
                anchor: '100%',
                xtype: 'textfield',
                msgTarget: 'side',
                labelWidth: 100
            },
            items: [
            {
                xtype: 'hiddenfield',
                name: 'master_class_id',
                fieldLabel: 'Label',
                bind : '{currentMasterClass.master_class_id}'
            },{
                fieldLabel: 'Название',
                allowBlank: false,
                maxLength: 200,
                name: 'name',
                bind: '{currentMasterClass.name}'
            },{
                fieldLabel: 'Преподаватель',
                maxLength: 150,
                name: 'teacher_name',
                bind: '{currentMasterClass.teacher_name}'
            },{
                xtype: 'textareafield',
                fieldLabel: 'Описание',
                maxLength: 1000,
                name: 'description',
                bind : '{currentMasterClass.description}'
            },{
                xtype: 'datefield',
                format: 'd.m.Y H:i',
                fieldLabel: 'Дата',
                name: 'date',
                minValue: new Date(),
                allowBlank: false,
                bind : '{currentMasterClass.date}'
            },{
                fieldLabel: 'Стоимость',
                name: 'coast',
                allowBlank: false,
                bind: '{currentMasterClass.coast}'
            },{
                xtype: 'filefield',
                fieldLabel: 'Фото',
                name: 'picture',
                buttonText: 'Выбрать фото...',
                afterLabelTextTpl: '',
                listeners: {
                    change: 'onFileFieldChange'
                }
            }]
        },{
            xtype: 'fieldset',
            title: 'Фото',
            width: 220,
            items: [{
                xtype: 'image',
                reference: 'masterClassPicture',
                height: 210,
                width: 200,
                bind:{
                    src: '../../{currentMasterClass.img_path}'
                }
            }]
        }]
    }],

    dockedItems: [{
        xtype: 'toolbar',
        dock: 'bottom',
        layout: {
            pack: 'end',
            type: 'hbox'
        },
        items: [
        {
        xtype: 'button',
        text: 'Сохранить',
        glyph: 'xf00c@FontAwesome',
        listeners: {
            click: 'onSave'
        }
        },{
            xtype: 'button',
            text: 'Закрыть',
            glyph: 'xf0e2@FontAwesome',
            listeners: {
                click: 'onCancel'
            }
        }]
    }]
});