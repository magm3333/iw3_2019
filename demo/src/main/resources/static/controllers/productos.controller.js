angular.module('iw3')

.controller('productos', function($scope, $rootScope, productosService){
	$scope.titulo="Productos";
	
	$scope.data=[];
	$scope.refresh=function() {
		productosService.list().then(
			function(resp){
				$scope.data=resp.data;
			},
			function(err){}
		);
	}
	$scope.init=function() {
		$scope.refresh();
	}
	
	
	$rootScope.authInfo($scope.init,false,false);
	
}); //End main controller