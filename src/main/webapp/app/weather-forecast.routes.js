(function() {
    'use strict';

    angular.module('weather-forecast-app')
           .config(UiRouterConfig);
		
	UiRouterConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
	function UiRouterConfig($stateProvider, $urlRouterProvider) {
	
		$urlRouterProvider.otherwise('/');
		
		$stateProvider
            .state('/',{
                url : '/',
                templateUrl: 'app/components/cityList/cityList.html',
                controller: 'CityListController',
                controllerAs: 'cityListCtrl'
            }).state('addCity',{
                url : '/city',
                templateUrl: 'app/components/city/city.html',
                controller: 'CityController',
                controllerAs: 'cityCtrl'
            }).state('forecast',{
                url : '/forecast/{cityName}',
                templateUrl: 'app/components/forecast/forecast.html',
                controller: 'ForecastController',
                controllerAs: 'forecastCtrl'
            });

	}
})();