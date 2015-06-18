angular
    .module('bigcam-app')
    .controller('listCtrl', ['$scope', 'biGCams', '$http', function($scope, biGCams, $http){
        $scope.biGCams = biGCams;
        $scope.hasOlder = false;
        $scope.hasMore = false;

        $scope.currPage=1;
        $scope.currPageDisabled='disabled';
        $scope.nextPageDisabled='';

        $scope.pageSize=30;

        $scope.deleteVideo=function(idx) {
            var biGCam = $scope.biGCams[idx];
            $http.get("video/delete/" + biGCam.id).success(function(response) {
                $scope.biGCams.splice(idx, 1);
            });
        };

        $scope.older=function() {
            if($scope.currPageDisabled == 'disabled') {
                return;
            }
            $scope.currPage=$scope.currPage-1;
            $scope.nextPageDisabled='';
            if($scope.currPage == 1) {
                $scope.currPageDisabled='disabled';
            } else{
                $scope.currPageDisabled='';
            }
            $http.get("/video/list?start=" + $scope.currPage + "&limit=" + $scope.pageSize).success(function(response) {
                $scope.biGCams = response;
            });
        };

        $scope.newer=function() {
            if($scope.nextPageDisabled == 'disabled') {
                return;
            }
            $scope.currPage=$scope.currPage+1;
            $scope.currPageDisabled='';
            $http.get("/video/list?start=" + $scope.currPage + "&limit=" + $scope.pageSize).success(function(response) {
                if(response.length < $scope.pageSize) {
                    $scope.nextPageDisabled='disabled';
                    if(response.length == 0) {
                        //no more
                        $scope.currPage=$scope.currPage-1;
                        return;
                    }
                }
                $scope.biGCams = response;
            });
        };
    }]);