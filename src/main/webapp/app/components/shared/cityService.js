(function() {
    'use strict';

    angular.module('weather-forecast-app')
    	   .factory('CityService', CityService);
    
    CityService.$inject = ['$resource'];
    function CityService($resource) {
    	return $resource('/cities/:name');
	}

})();