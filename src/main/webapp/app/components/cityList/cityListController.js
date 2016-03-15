(function() {
	'use strict';

	angular.module('weather-forecast-app')
		   .controller('CityListController', CityListController);

	CityListController.$inject = ['$state', 'CityService'];
	function CityListController($state, CityService) {
		
		var vm = this;
		vm.cityList = CityService.query();
		vm.deleteCity = deleteCity;
		
		function deleteCity(city) {
			if(confirm("Confirma a exclusao da cidade '" + city.name + "'?")) {
				city.$delete({name : city.id}).then(function() {
					vm.cityList = CityService.query();
				});
			}
		}

	}
})();