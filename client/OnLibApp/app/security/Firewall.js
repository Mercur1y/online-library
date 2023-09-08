Ext.define('OnLibApp.security.Firewall', {
    singleton: true,
    requires: [
        'OnLibApp.security.TokenStorage'
    ],

    isLoggedIn: function() {
        return null !== OnLibApp.security.TokenStorage.retrieve();
    },

    login: function(username, password) {
        var deferred = new Ext.Deferred();

        Ext.Ajax.request({
            url: 'api/v1/auth/signin',
            method: 'POST',
            jsonData: {
                'username': username,
                'password': password
            },

            success: function (response) {
                var data = Ext.decode(response.responseText);
                if (data.token) {
                    OnLibApp.security.TokenStorage.save(data.token);
                    deferred.resolve(data, response);
                } else {
                    deferred.reject(data, response);
                }
            },

            failure: function (response) {
                var data = Ext.decode(response.responseText);

                OnLibApp.security.TokenStorage.clear();

                deferred.reject(data, response);
            }
        });

        return deferred.promise;
    },

    logout: function(callback) {
        OnLibApp.security.TokenStorage.clear();
        callback();
    }
}, function () {
    Ext.Ajax.on('beforerequest', function(conn, options) {
        if (OnLibApp.security.Firewall.isLoggedIn() ){
            options.headers = options.headers || {};
            options.headers['Authorization'] = 'Bearer ' + OnLibApp.security.TokenStorage.retrieve();
        }
    });
});