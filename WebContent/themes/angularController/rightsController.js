var rightsApp = angular.module('rightsApp', []);
rightsApp.controller('rightsController', function($http,$scope) {
	 $http.get('/data/leagl.html').success(function(res){
		 $scope.recommendList = res.data;
     }).error(function (data,status,headers,config){});
});