/**
 * Created by Henrikh on 4/4/16.
 */

app.controller('SurveyCtrl', ['$scope', '$http', 'RestURL',
  function ($scope, $http, RestURL) {
    var self = $scope;

    self.init = function () {
      self.searchAllCountries();
      self.getAllCompanies();
      self.getAllSurveys();
    };

    self.createSurvey = function () {
      var new_survey = {}, i;
      /* validation */
      var valid = self.new_survey_name && self.new_survey_company && self.new_survey_country
        && self.new_survey_product && self.new_survey_description;

      if (valid) {
        /* create new survey */
        new_survey['id'] = 0;
        new_survey['status'] = 'C';
        new_survey['survey_name'] = self.new_survey_name;
        new_survey['client_id'] = self.new_survey_company;
        new_survey['country_id'] = self.new_survey_country;
        new_survey['product_id'] = self.new_survey_product;
        new_survey['survey_description'] = self.new_survey_description;
        new_survey['questions'] = [];

        for (i = 0; i < self.countries.length; i++) {
          if (new_survey['country_id'] == self.countries[i]['id']) {
            new_survey['country_name'] = self.countries[i]['common_name'];
          }
        }

        for (i = 0; i < self.clients.length; i++) {
          if (new_survey['client_id'] == self.clients[i]['id']) {
            new_survey['client_name'] = self.clients[i]['client_name'];
          }
        }

        for (i = 0; i < self.products.length; i++) {
          if (new_survey['product_id'] == self.products[i]['id']) {
            new_survey['product_name'] = self.products[i]['product_name'];
          }
        }

        /* http request for creating a survey */
        $http.post(RestURL.baseURL + 'Survey_Default_Activity/create_survey_for_desktop', JSON.stringify(new_survey))
          .success(function (response) {
            console.log(response);
            /* refresh all surveys */
            self.getAllSurveys();
          })
          .error(function (err) {
            console.error(err);
          });

        /* hide modal */
        $('#create_survey_modal').modal('hide');

      } else {
        /* if not valid show alert */
        alert('Please fill in all the fields');
      }
    };

    /* get all Surveys */
    self.getAllSurveys = function () {
      $http.get(RestURL.baseURL + 'Survey_Default_Activity/get_all_Survey_for_desktop')
        .success(function (response) {
          self.surveys = response;
          console.log(self.surveys);
        })
        .error(function (err) {
          console.error(err);
        });
    };

    self.searchAllCountries = function () {
      $http.get(RestURL.baseURL + 'Country/search_all_Country')
        .success(function (response) {
          self.countries = response;
        })
        .error(function (err) {
          alert('You got' + err + 'error');
        });
    };

    self.getAllCompanies = function () {
      $http.get(RestURL.baseURL + 'Client_Default_Activity/get_all_Client')
        .success(function (response) {
          self.clients = response;
        })
        .error(function (err) {
          alert('You got' + err + 'error');
        });
    };

    self.getProductsByCompany = function () {
      $http.get(RestURL.baseURL + 'Product_Default_Activity/get_product_by_client_id/' + self.new_survey_company)
        .success(function (response) {
          self.products = response;
        })
        .error(function (err) {
          alert('You got' + err + 'error');
        });
    };

    self.init();

  }]);