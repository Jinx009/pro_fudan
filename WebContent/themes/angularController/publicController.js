var publicApp = angular.module('publicApp', []);
publicApp.controller('publicController', function($http,$scope) {
	 $http.get('/data/articleContent/type.html?typeId=2').success(function(res){
		 console.log(res)
		 if(null!=res.data){
				for(var i in res.data){
					if(null!=res.data[i].imgUrl&&''!=res.data[i].imgUrl){
						res.data[i].publicPicUrl = res.data[i].imgUrl;
					}else{
						res.data[i].publicPicUrl = '/themes/img/recommend_img.png';
					}
					res.data[i].publicTitle = res.data[i].title;
					res.data[i].publicDesc = res.data[i].contentBase;
					res.data[i].url = res.data[i].content;
				}
			}
		 $scope.recommendList = res.data;
     }).error(function (data,status,headers,config){});
});