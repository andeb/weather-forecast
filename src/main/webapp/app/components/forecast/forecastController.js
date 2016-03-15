(function() {
	'use strict';

	angular.module('weather-forecast-app')
		   .controller('ForecastController', ForecastController);

	ForecastController.$inject = ['$stateParams', 'ForecastService'];
	function ForecastController($stateParams, ForecastService) {
		
		var vm = this;
		vm.cityName = "";
		vm.forecastList = {};
		
		init();

		function init() {
			vm.cityName = $stateParams.cityName;
			if(vm.cityName) {
				vm.forecastList = ForecastService.get({name : vm.cityName});
			}
		}
		
	}
})();