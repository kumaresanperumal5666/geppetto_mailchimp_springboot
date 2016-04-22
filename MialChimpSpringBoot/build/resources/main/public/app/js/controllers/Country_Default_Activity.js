/**
 *
 * @author Schubert Generated Code</br>
 * Date Created: </br>
 * @since  </br>
   build:   </p>
 *
 * code was generated by the Schubert System </br>
 * Schubert system Copyright - NewPortBay LLC copy_right_range</br>
 * The generated code is free to use by anyone</p>
 *
 *
 *
*/

app.controller("Country_Default_Activity", [ '$scope', '$rootScope', '$location', '$window', '$q', '$http', 'CountryId', 'RestURL',
				    function( $scope, $rootScope, $location, $window, $q, $http, CountryId, RestURL) {

		$scope.Country = {
		id: '',
		common_name : '', 
		formal_name : '', 
		iso_three_letter_code : '', 
		capital : '', 
		currency_name : '', 
		telephone_code : ''
		};

		$scope.allCountry = null;

		$scope.init = function(){
			
			 $scope.search_all_country();
			
		}
		
		
		$scope.closeModal = function () {
			
			$scope.search_all_country();
		}
		
		$scope.setCountry = function(selectedCountry) {
			if (selectedCountry === undefined || selectedCountry === null  ) {
				$scope.Country = {
						id: '',
						common_name : '', 
						formal_name : '', 
						iso_three_letter_code : '', 
						capital : '', 
						currency_name : '', 
						telephone_code : ''
						};
			} else {
				$scope.Country = selectedCountry;
			}
				
		}
		
        $scope.search_for_update = function (id) {

		  //this is where the start code goes

		  //this is where the validate code goes

		  //this is where the confirm code goes

		  //this is where the post code goes
		  $http.get(RestURL.baseURL+'Country/search_for_update_Country/{Country_id}/' + id).success(function(response) {
		  	 console.log('Operation search_for_update Country successful');
		  $scope.Country.id = response.id;
		  $scope.Country.common_name = response.common_name;
		  $scope.Country.formal_name = response.formal_name;
		  $scope.Country.iso_three_letter_code = response.iso_three_letter_code;
		  $scope.Country.capital = response.capital;
		  $scope.Country.currency_name = response.currency_name;
		  $scope.Country.telephone_code = response.telephone_code;
		  CountryId.setId(undefined)
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  });

		  //this is where the server response code goes

		  //this is where the display server response code goes

		  //this is where the transition code goes

		  //this is where the end code goes

        };

        $scope.update = function () {

		  //this is where the start code goes

		  //this is where the validate code goes

		 // this is where the confirm code goes

		  //this is where the post code goes
		  var deferred = $q.defer();
		  $http.put(RestURL.baseURL+'Country/update_Country', $scope.Country).success(function(response) {
		  	 alert('Country updated successfully');
		  	 $scope.search_all_country();
			  $('#create_country_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  //this is where the server response code goes

		  //this is where the display server response code goes

		  //this is where the transition code goes

		  //this is where the end code goes 
		  return deferred.promise; 


        };

        $scope.delete = function () {

		  //this is where the start code goes

		  //this is where the validate code goes

		  //this is where the confirm code goes

		  //this is where the post code goes
		  var deferred = $q.defer();
		  $http.delete(RestURL.baseURL+'Country/delete_Country/'+$scope.Country.id).success(function(response) {
		  	 alert('Country deleted successfully');
		  	$scope.search_all_country();
			  $('#create_country_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  //this is where the server response code goes

		  //this is where the display server response code goes

		  //this is where the transition code goes

		  //this is where the end code goes 
		  return deferred.promise; 


        };

        $scope.create = function () {

		  var deferred = $q.defer();
		  $http.post(RestURL.baseURL+'Country/create_Country/', $scope.Country).success(function(response) {
		  	 alert('Country created successfully');
		  	 $scope.search_all_country();
		  	$('#create_country_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  return deferred.promise; 


        };

        $scope.search = function () {

		  //this is where the start code goes
		  	$scope.Country.common_name = $scope.Country.common_name? $scope.Country.common_name:"%%";
		  	$scope.Country.formal_name = $scope.Country.formal_name? $scope.Country.formal_name:"%%";
		  	$scope.Country.iso_three_letter_code = $scope.Country.iso_three_letter_code? $scope.Country.iso_three_letter_code:"%%";
		  	$scope.Country.capital = $scope.Country.capital? $scope.Country.capital:"%%";
		  	$scope.Country.currency_name = $scope.Country.currency_name? $scope.Country.currency_name:"%%";
		  	$scope.Country.telephone_code = $scope.Country.telephone_code? $scope.Country.telephone_code:"%%";


		  //this is where the validate code goes

		  //this is where the confirm code goes

		  var deferred = $q.defer();
		  $http.get(RestURL.baseURL+'Country/search_Country?common_name='+$scope.Country.common_name+'&formal_name='+$scope.Country.formal_name+'&iso_three_letter_code='+$scope.Country.iso_three_letter_code+'&capital='+$scope.Country.capital+'&currency_name='+$scope.Country.currency_name+'&telephone_code='+$scope.Country.telephone_code).success(function(response) {
		  console.log(response);
		  var search_result = [];
		  response.forEach(function(entry) {
		  var row = {'common_name':entry.common_name,'formal_name':entry.formal_name,'iso_three_letter_code':entry.iso_three_letter_code,'capital':entry.capital,'currency_name':entry.currency_name,'telephone_code':entry.telephone_code};
		  search_result.push(row);
		  });
		  handle_search_result(search_result);
		  }).error(function(err) {
		  alert('You got' + err + 'error');
		  deferred.reject(err);
		  });

		  //this is where the server response code goes

		  //this is where the display server response code goes

		  //this is where the transition code goes

		  //this is where the end code goes 
		  return deferred.promise; 


        };
        
        
        $scope.search_all_country = function () {

  		  $http.get(RestURL.baseURL+'Country/search_all_Country').success(function(response) {
	  		  $scope.allCountry = response;
  		  }).error(function(err) {
	  		  alert('You got' + err + 'error');
  		  });

          };


          $scope.init();

		/*var id = CountryId.getId();
		if (id) {
		console.log(id);
		$scope.search_for_update(id);
		} else {
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
		$scope.searchForCountry = function () {
		if ($scope.idForCountry) {
		$('#myModal').modal('hide');
		id = $scope.idForCountry;
		console.log(id);
		$scope.search_for_update(id);
		}	else {
		alert('You should enter ID');
		}
		};
		}*/

		$scope.$on('$viewContentLoaded', function(event) {
			var biggestHeight = 0;
			var height = 0;
			$(".screen").find('*').each(function(){
				height = $(this).position().top + $(this).height() + 100;
				if (height > biggestHeight ) {
					biggestHeight = height;
				}
			});
			$(".screen").height(biggestHeight);
		});

}]);

