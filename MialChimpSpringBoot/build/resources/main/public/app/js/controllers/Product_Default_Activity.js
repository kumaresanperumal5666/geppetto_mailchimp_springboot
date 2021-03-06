/**
 * 
 * @author Schubert Generated Code</br> Date Created: </br>
 * @since </br> build:
 *        </p>
 * 
 * code was generated by the Schubert System </br> Schubert system Copyright -
 * NewPortBay LLC copy_right_range</br> The generated code is free to use by
 * anyone
 * </p>
 * 
 * 
 * 
 */

app.controller("Product_Default_Activity", [ '$scope', '$rootScope', '$location', '$window', '$q', '$http', 'ProductId', 'RestURL',
				    function( $scope, $rootScope, $location, $window, $q, $http, ProductId, RestURL) {

		$scope.product = {
			id: '',
			product_name : '', 
			product_notes : '', 
			created_by : '', 
			created_date : '', 
			modified_by : '', 
			modified_date : '',
			client_id:''
		};
	
		$scope.init = function () {
			$scope.getAllClient();
		}
        
		$scope.closeModal = function () {
			$scope.getClientProducts();
		}
		
		$scope.setProduct = function(selectedProduct) {
			if (selectedProduct === undefined || selectedProduct === null  ) {
				var selectedClient_id = $scope.product.client_id;
				$scope.product = {
						id: '',
						product_name : '', 
						product_notes : '', 
						created_by : '', 
						created_date : '', 
						modified_by : '', 
						modified_date : '',
						client_id:''
						};
				$scope.product.client_id = selectedClient_id;
			} else {
				$scope.product = selectedProduct;
			}
		}
		
		$scope.getAllClient = function() {
        	$http.get( RestURL.baseURL+'Client_Default_Activity/get_all_Client').success(function(response) {
        		console.log("Product -> getAllClient -> " + JSON.stringify( response ));
        		$scope.allClients = response;
        	}).error(function(err) {
   		  	 alert('You got' + err + 'error');
  		  });
        };
        
        $scope.client = null;
        $scope.setClient = function() {
        	console.log("Product -> setClient -> $scope.client: " + JSON.stringify( $scope.client ));
        	if (!angular.isUndefined($scope.client) && $scope.client != null) {
        		$scope.product.client_id = $scope.client.id;
            
        		$scope.getClientProducts();
        	} else {
        		$scope.product.client_id = '';
            	$scope.clientProducts = null;
        	}
        	
        };
		
		$scope.getClientProducts = function() {
			console.log("Product -> getClientProducts -> $scope.product.client_id: " + $scope.product.client_id);
        	$http.get( RestURL.baseURL+'Product_Default_Activity/get_product_by_client_id/'+ $scope.product.client_id ).success(function(response) {
        		console.log("Product -> getClientProducts -> " + JSON.stringify( response ));
        		$scope.clientProducts = response;
        	}).error(function(err) {
   		  	 alert('You got' + err + 'error');
  		  });
        };
        
        $scope.create = function () {

		  var deferred = $q.defer();
		  $http.post(RestURL.baseURL+'Product_Default_Activity/create_Product/', $scope.product).success(function(response) {
		  	 alert('Product created successfully');
			  	$scope.getClientProducts();
			  	$('#create_product_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  return deferred.promise; 


        };

        $scope.search_for_update = function (id) {

		  $http.get(RestURL.baseURL+'Product_Default_Activity/search_for_update_Product/' + id).success(function(response) {
		  	 console.log('Product updated successfully');
		  	 $scope.product = response
		  	 
		  	 //ProductId.setId(undefined)

		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  });

        };

        $scope.update = function () {

		  var deferred = $q.defer();
		  $http.put(RestURL.baseURL+'Product_Default_Activity/update_Product', $scope.product).success(function(response) {
		  	 alert('Product updated successfully.');
			  	$scope.getClientProducts();
			  	$('#create_product_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  return deferred.promise; 


        };

        $scope.delete = function () {

		  var deferred = $q.defer();
		  $http.delete(RestURL.baseURL+'Product_Default_Activity/delete_Product/'+$scope.product.id).success(function(response) {
		  	 alert('Product deleted successfully');
		  	$scope.getClientProducts();
		  	$('#create_product_modal').modal('hide');
		  	 deferred.resolve(response);
		  }).error(function(err) {
		  	 alert('You got' + err + 'error');
		  	 deferred.reject(err);
		  });

		  return deferred.promise; 


        };

        $scope.get_all_values = function () {

        };

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

		$scope.init();
}]);


