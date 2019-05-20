app.service("service",function($http){

    //查询图书对象
    this.findBookById=function () {
        return $http.get("../book/findBookById.do");
    };

    //初始化内容
    this.findBookContent=function () {
        return $http.get("../book/findBookContent.do?id="+id);
    }
});