/**
 * Directives
 */

var SeriesWeb = angular.module('SeriesWeb',['ngRoute','SerieWebControllers','ngCookies','ngFileUpload']).run(run);

SeriesWeb
.directive('homeRead', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/read/Home.html',
		controller: 'HomeController',
		controllerAs: 'homes'
	};
})
.directive('userUpdate', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/User.html'
	};
})	
.directive('actorUpdate', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/Actor.html'
	};
})
.directive('serieUpdate', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/Serie.html'
	};
})
.directive('serieAddActor', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/AddActor.html'
	};
})	
.directive('serieAddEpisode', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/AddEpisode.html'
	};
})
.directive('episodeUpdate', function () {
	return {
		restrict: 'E',
		templateUrl: '/SeriesWeb/view/update/Episode.html'
	};
})
.directive('material', function() {
	return{
		link: function( scope, element, attrs){
			$.material.init();
		}
	}
});

/**
 * Router
 */

SeriesWeb.config([ '$routeProvider', '$locationProvider',function( $routeProvider, $locationProvider) {
	$routeProvider
	.when('/home', {
		templateUrl: "/SeriesWeb/home.html"
	})
	.when('/login', {
		templateUrl: "/SeriesWeb/login.html",
		controller: "LoginController",
		controllerAs: "logins"
	})
	.when('/logout', {
		templateUrl: "/SeriesWeb/logout.html",
		controller: "LoginController",
		controllerAs: "logins"
	})
	.when('/createUser', {
		templateUrl: "/SeriesWeb/view/create/User.html",
		controller: "UserController",
		controllerAs: "users"
	})
	.when('/readUser', {
		templateUrl: "/SeriesWeb/view/read/User.html",
		controller: "UserController",
		controllerAs: "users"
	})
	.when('/createActor', {
		templateUrl: "/SeriesWeb/view/create/Actor.html",
		controller: "ActorController",
		controllerAs: "actors"
	})
	.when('/readActor', {
		templateUrl: "/SeriesWeb/view/read/Actor.html",
		controller: "ActorController",
		controllerAs: "actors"
	})
	.when('/createSerie', {
		templateUrl: "/SeriesWeb/view/create/Serie.html",
		controller: "SerieController",
		controllerAs: "series"
	})
	.when('/readSerie', {
		templateUrl: "/SeriesWeb/view/read/Serie.html",
		controller: "SerieController",
		controllerAs: "series"
	})
	.when('/createEpisode', {
		templateUrl: "/SeriesWeb/view/create/Episode.html",
		controller: "EpisodeController",
		controllerAs: "episodes"
	})
	.when('/readEpisode', {
		templateUrl: "/SeriesWeb/view/read/Episode.html",
		controller: "EpisodeController",
		controllerAs: "episodes"
	})
	.otherwise({
		redirectTo: '/login'
	});
}]);

run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
function run($rootScope, $location, $cookies, $http) {
    // keep user logged in after page refresh
    if ($cookies.get('user') != null) {
        $location.path('/home');
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login','/createUser']) === -1;
        var loggedIn = $cookies.get('user');
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
}