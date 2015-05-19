angular
    .module('bigcam-app')
    .controller('uploadCtrl', ['$scope', 'channels', function($scope, channels){
        $scope.channels = channels;
        $scope.selectedValue = channels[0];
    }]);