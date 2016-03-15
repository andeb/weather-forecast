(function() {
	'use strict';

	angular.module('weather-forecast-app')
		   .controller('CityController', CityController);

	CityController.$inject = ['$state', '$stateParams', 'CityService'];
	function CityController($state, $stateParams, CityService) {
		
		var vm = this;
		vm.city = new CityService();
		vm.hasError = false;
		vm.saveCity = saveCity;
		
		init();

		function init() {
			var cityName = $stateParams.cityName;
			if(cityName) {
				vm.city = CityService.get({name : cityName});
			}
		}
		
		function saveCity() {
			vm.hasError = false;
			
			vm.city.$save(function(){
				alert("Sucesso!");
				$state.go('/');
			},function(err) {
				showError()
			});
		}
		
		function showError() {
			vm.hasError = true;
		}
		
	}
})();