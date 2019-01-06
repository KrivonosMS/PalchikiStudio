Ext.define('AdminPanel.view.login.Login', {
    extend: 'Ext.panel.Panel',
    xtype: 'login-dialog',

    requires: [
        'AdminPanel.view.login.LoginController'
    ],

    plugins: 'viewport',

    controller: 'login',

    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },

    bodyStyle: {
        background: '#FFF7E7'
    },

    items: [{
        xtype: 'form',
        reference: 'form',
        title: 'Авторизация',
        iconCls: 'fa fa-key fa-lg',
        height: 250,
        width: 500,
        marginPadding: 15,
        frame: true,
        layout: {
            type: 'vbox',
            align: 'middle',
            pack: 'center'
        },
        defaults: {
            xtype: 'textfield',
            padding: 5,
            labelWidth: 60,
            allowBlank: false,
            minLength: 3,
            msgTarget: 'under',
            width: 450,
        },
        items: [{
            name: 'user',
            fieldLabel: 'Логин',
            maxLength: 25,
            vtype: 'alphanum',
            listeners: {
                specialKey: 'onTextFieldSpecialKey'
            }
        },{
            inputType: 'password',
            name: 'password',
            fieldLabel: 'Пароль',
            maxLength: 15,
            id: 'password',
            enableKeyEvents: true,
            listeners: {
                specialKey: 'onTextFieldSpecialKey',
                keypress: 'onTextFieldKeyPress'
            }
        }],

        dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            items: [{
                xtype: 'tbfill',
            },{
                xtype: 'button',
                text: 'Очистить',
                iconCls: 'fa fa-times fa-lg',
                listeners: {
                    click: 'onButtonClickCancel'
                }
            },{
                xtype: 'button',
                formBind: true,
                text: 'Войти',
                iconCls: 'fa fa-sign-in fa-lg',
                listeners: {
                    click: 'onButtonClickSubmit'
                }
            }]
        }]
    }],
});