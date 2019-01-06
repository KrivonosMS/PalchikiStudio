Ext.define('AdminPanel.view.event.MasterClassGrid' ,{
    extend: 'Ext.grid.Panel',
    xtype: 'masterclassgrid',

    requires: [
        'AdminPanel.view.event.MasterClassViewModel'
    ],

    bind : '{masterClass}',

    reference: 'masterclassgrid',

    columns: [{
        header: 'Название',
        dataIndex: 'name',
        flex: 4
    },{
        header: 'Преподаватель',
        dataIndex: 'teacher_name',
        flex: 4
    },{
        header: 'Описание',
        dataIndex: 'description',
        flex: 10
    },{
        header: 'Дата проведения',
        dataIndex: 'date',
        flex: 2
    },{
        header: 'Стоимость',
        dataIndex: 'coast',
        flex: 1,
        renderer: function(value) {
            return value + ' &#8381;'
        }
    },{
        xtype: 'actioncolumn',
        width: 25,
        items: [{
            icon:'resources/images/modify.png',
            handler: 'onEdit'
        }]
    },{
        xtype: 'actioncolumn',
        width: 25,
        items: [{
            icon: 'resources/images/erase.png',
            handler: 'onDelete'
        }]
    }],
    bbar: {
        xtype: 'pagingtoolbar',
        bind: {
            store: '{masterClass}'
        },
        displayInfo: true
    },
    listeners: {
        celldblclick: 'showDescription'
    }
});