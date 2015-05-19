angular
    .module('bigcam-app')
    .controller('listCtrl', ['$scope', 'biGCams', function($scope, biGCams){
        $scope.biGCams = biGCams;
        $scope.hasOlder = false;
        $scope.hasMore = false;
    }]);