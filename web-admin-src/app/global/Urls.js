var urls = (function(){
    function isLocalhost() {
        return window.location.href.indexOf('http://localhost:1841') === 0;
    };

    function createUrl(action) {
        return window.location.href + 'data/' + action;
    };

    return {
        logout: isLocalhost() ? 'resources/data/logout.json' : createUrl('logout'),
        login: isLocalhost() ? 'resources/data/login.json' : createUrl('login'),
        master_class: isLocalhost() ? 'resources/data/master_class.json' : createUrl('master_class'),
        save: isLocalhost() ? 'resources/data/save.json' : createUrl('save'),
        delete: isLocalhost() ? 'resources/data/delete.json' : createUrl('delete')
    };
}())