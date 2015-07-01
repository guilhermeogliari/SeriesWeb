/**
 * Controllers
 */

var SeriesWebControllers = angular.module("SerieWebControllers",[]);


/**
 * Home 
 */

SeriesWebControllers.controller("HomeController", ['$scope', '$http', '$location','$cookies',function($scope, $http,$location,$cookies) {
	
	var home = this;
	var host = 'api/Serie/';
	
	home.listHome = function() {
		$http["get"](host).success(function(data) {
			home.query = data;
		});
	};
	
	home.assisted = function($serie) {
		var iteration = null;
		$http["get"]('api/Iteration').success(function(data) {
				
		});
	};
	
	home.listHome();
	
}]);

/**
 * Login 
 */

SeriesWebControllers.controller("LoginController", ['$scope', '$http', '$location', '$cookies',function($scope, $http,$location,$cookies) {
	
	var login = this;
	var host = 'api/User/';
	
	login.LoginExec = function($login) {
		$http["get"](host).success(function(data) {
			for ( var user in data) {
				if(data[user].email === $login.email){
					if(data[user].password === $login.password){
						$cookies.put('user', data[user]);
						$location.url('/home');
					}else{
						login.message = "Senha incorreta!";
					}
				}else{
					login.message = "Email incorreto!";
				}
			}
		});
	};
	
	login.logout = function() {
		$cookies.remove('user');
		$location.path('/');
	};
	
}]);

/**
 * User 
 */

SeriesWebControllers.controller("UserController", ['$scope', '$http', '$location',function($scope, $http,$location) {
	
	var user = this;
	var host = 'api/User/';
	
	user.listUser = function() {
		$http["get"](host).success(function(data) {
			user.query = data;
		});
	};
	
	user.addUser = function($user) {
		$http["post"](host, $user).success(function(data) {
			$location.url('/readUser');
			user.listUser();
		});
	};
	
	user.updUser = function($user) {
		if($user.password2 != null){
			$user.password = $user.password2;
		}
		$http["put"](host+$user.id, $user).success(function(data) {
			user.listUser();
		});
	};
	
	user.delUser = function($user){
		$http["delete"](host+$user).success(function(data) {
			user.listUser();
		});
	};
	
	user.listUser();
	
}]);

/**
 * Actor
 */

SeriesWebControllers.controller("ActorController", ['$scope', '$http', '$location',function($scope, $http,$location) {
	
	var actor = this;
	var host = 'api/Actor/';
	
	actor.listActor = function() {
		$http["get"](host).success(function(data) {
			actor.query = data;
		});
	};
	
	actor.addActor = function($actor) {
		$http["post"](host, $actor).success(function(data) {
			$location.url('/readActor');
			actor.listActor();
		});
	};
	
	actor.updActor = function($actor) {
		$http["put"](host+$actor.id, $actor).success(function(data) {
			actor.listActor();
		});
	};
	
	actor.delActor = function($actor){
		$http["delete"](host+$actor).success(function(data) {
			actor.listActor();
		});
	};
	
	actor.listActor();
	
}]);


/**
 * Serie
 */

SeriesWebControllers.controller("SerieController", ['$scope', '$http', '$location','Upload',function($scope, $http,$location,Upload) {
	
	var serie = this;
	var host = 'api/Serie/';
	
	serie.listSerie = function() {
		$http["get"](host).success(function(data) {
			serie.query = data;
		});
	};
	
	serie.upload = function (files, name) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                Upload.upload({
                    url: 'api/files/upload/',
                    fields: {'username': name},
                    file: file
                }).progress(function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
                }).success(function (data, status, headers, config) {
                    console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                });
            }
        }
    };
	
	serie.listGenre = function() {
		$http["get"]('api/Genre').success(function(data) {
			serie.genres = data;
		});
	};
	
	serie.listActor = function() {
		$http["get"]('api/Actor').success(function(data) {
			serie.actor = data;
		});
	};
	
	serie.listEpisode = function() {
		$http["get"]('api/Episode').success(function(data) {
			serie.episode = data;
		});
	};
	
	serie.addSerie = function($serie) {
		serie.upload($serie.image, $serie.name);
		$http["post"](host, $serie).success(function(data) {
			$location.url('/readSerie');
			serie.listSerie();
		});
	};
	
	serie.updSerie = function($serie) {
		$http["put"](host+$serie.id, $serie).success(function(data) {
			serie.listSerie();
		});
	};
	
	serie.delSerie = function($serie){
		$http["delete"](host+$serie).success(function(data) {
			serie.listSerie();
		});
	};
	
	serie.listGenre();
	serie.listActor();
	serie.listEpisode();
	serie.listSerie();
	
}]);

/**
 * Episode
 */

SeriesWebControllers.controller("EpisodeController", ['$scope', '$http', '$location',function($scope, $http,$location) {
	
	var episode = this;
	var host = 'api/Episode/';
	
	episode.listSeries = function() {
		$http["get"]('api/Serie').success(function(data) {
			episode.series = data;
		});
	};
	
	episode.listEpisode = function() {
		$http["get"](host).success(function(data) {
			episode.query = data;
		});
	};
	
	episode.addEpisode = function($episode) {
		$http["post"](host, $episode).success(function(data) {
			$location.url('/readEpisode');
			episode.listEpisode();
		});
	};
	
	episode.updEpisode = function($episode) {
		$http["put"](host+$episode.id, $episode).success(function(data) {
			episode.listEpisode();
		});
	};
	
	episode.delEpisode = function($episode){
		$http["delete"](host+$episode).success(function(data) {
			episode.listEpisode();
		});
	};
	
	episode.listEpisode();
	episode.listSeries();
	
}]);
