(function() {
    'use strict';

    angular.module('weather-forecast-app')
    	   .factory('ForecastService', ForecastService);
    
    ForecastService.$inject = ['$resource'];
    function ForecastService($resource) {
    	return $resource('/forecast/:name', {}, {
    		'get': {
    			method	: 'GET',
    			url		: '/forecast/:name',
    			isArray : true
    		}
    	});
	}

})();