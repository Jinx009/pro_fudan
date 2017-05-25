var housekeepingApp = angular.module('housekeepingApp', []);
housekeepingApp.controller('housekeepingController', function($http,$scope) {
	 $http.get('/data/articleContent/type.html?typeId=3').success(function(res){
		 console.log(res)
		 if(null!=res.data){
				for(var i in res.data){
					if(null!=res.data[i].imgUrl&&''!=res.data[i].imgUrl){
						res.data[i].recommendPicUrl = res.data[i].imgUrl;
					}else{
						res.data[i].recommendPicUrl = '/themes/img/recommend_img.png';
					}
					res.data[i].recommendTitle = res.data[i].title;
					res.data[i].recommendDesc = res.data[i].contentBase;
					res.data[i].url = res.data[i].content;
				}
			}
		 $scope.recommendList = res.data;
     }).error(function (data,status,headers,config){});
});