angular
    .module('bigcam-app', [
        'ui.router'
    ]).factory('authInterceptorService', ['$q','$location', function ($q, $location){
        var responseError = function (rejection) {
            if (rejection.status === 403) {
                window.location.replace("/login.jsp");
            }
            return $q.reject(rejection);
        };

        return {
            responseError: responseError
        };
    }])
    .config(['$urlRouterProvider', '$stateProvider', '$httpProvider', function($urlRouterProvider, $stateProvider, $httpProvider){
        $httpProvider.interceptors.push('authInterceptorService');
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'static/templates/upload.html',
                controller: 'uploadCtrl',
                resolve: {
                    channels: ['$http', function($http){
                        return $http.get('/channel/list').then(function(response){
                            return response.data;
                        });
                    }],
                    uploadToken: ['$http', function($http){
                        return $http.get('/video/uploadToken').then(function(response){
                            return response.data;
                        });
                    }]
                }
            })
            .state('list', {
                url: '/list',
                templateUrl: 'static/templates/list.html',
                controller: 'listCtrl',
                resolve: {
                    biGCams: ['$http', function($http){
                        return $http.get('/video/list?start=0&limit=30').then(function(response) {
                            return response.data;
                        })
                    }]
                }
            })
            .state('suggestions', {
                url: '/suggestions',
                templateUrl: 'static/templates/suggestions.html',
                controller: 'suggestionsCtrl',
                resolve: {
                    suggestions: ['$http', function($http){
                        return $http.get('/suggestion/list?start=0&limit=30').then(function(response) {
                            return response.data;
                        })
                    }]
                }
            });
    }])