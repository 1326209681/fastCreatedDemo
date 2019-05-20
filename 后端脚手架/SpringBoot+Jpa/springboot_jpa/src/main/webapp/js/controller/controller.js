app.controller("controller",function($scope,service){


    //查询作者的基本信息；
    $scope.findBookById=function () {
        service.findBookById().success(
            function (response) {
                $scope.list=response;
            }
        )
    };
    //查询作品的第一页
    $scope.findBookContent=function (id) {
        service.findBookContent(id).success(
            function (response) {
                $scope.bookContent=response;
            }

        )
    }

});