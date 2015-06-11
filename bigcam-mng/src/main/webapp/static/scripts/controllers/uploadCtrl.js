angular
    .module('bigcam-app')
    .controller('uploadCtrl', ['$scope', 'channels', 'uploadToken', function($scope, channels, uploadToken){
        $scope.channels = channels;
        $scope.selectedValue = channels[0];
        $scope.uploadToken = uploadToken;
    }]);