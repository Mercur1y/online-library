Ext.define('OnLibApp.security.Firewall', {
    singleton: true,
    requires: [
        'OnLibApp.security.TokenStorage'
    ],

    isLoggedIn: function () {
        return null !== OnLibApp.security.TokenStorage.retrieve();
    },

    getCurrentUsername: function () {
        return parseJwt(OnLibApp.security.TokenStorage.retrieve()).sub;
    },

    login: function (username, password) {
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

    register: function (username, password, email, name, lastName) {
        var deferred = new Ext.Deferred();

        Ext.Ajax.request({
            url: 'api/v1/auth/signup',
            method: 'POST',
            jsonData: {
                'username': username,
                'password': password,
                'email': email,
                'name': name,
                'lastName': lastName
            },

            success: function (response) {
                var data = Ext.decode(response.responseText);
                deferred.resolve(data, response);
            },

            failure: function (response) {
                var data = Ext.decode(response.responseText);
                deferred.reject(data, response);
            }
        });

        return deferred.promise;
    },

    logout: function () {
        OnLibApp.security.TokenStorage.clear();
    }
}, function () {
    Ext.Ajax.on('beforerequest', function (conn, options) {
        if (OnLibApp.security.Firewall.isLoggedIn()) {
            options.headers = options.headers || {};
            options.headers['Authorization'] = 'Bearer ' + OnLibApp.security.TokenStorage.retrieve();
        }
    });
});

const parseJwt = (token) => {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
};