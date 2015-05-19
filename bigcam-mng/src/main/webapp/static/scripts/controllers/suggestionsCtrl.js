angular
    .module('bigcam-app')
    .controller('suggestionsCtrl', ['$scope', 'suggestions', function($scope, suggestions){
        $scope.suggestions = suggestions;
        $scope.hasOlder = false;
        $scope.hasMore = false;
    }]);