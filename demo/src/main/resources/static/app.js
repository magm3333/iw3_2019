angular.module('iw3',[ 'ngRoute', 'ngSanitize', 'ngAnimate', 'ngTouch', 'ui.bootstrap',
	'ngSanitize', 'angularUtils.directives.dirPagination',
	'angucomplete-alt', 'ngLoadingSpinner', 'ui.select',
	'ngDragDrop', 'ui-notification',
	'chart.js', 'ngStomp', 'uiSwitch', 'xeditable', 'ngStorage', 
	'oitozero.ngSweetAlert', 'dialogs.main' ]);

var app=angular.module('iw3');

app.controller('controlador1',function($scope){
	$scope.numero=13;
});