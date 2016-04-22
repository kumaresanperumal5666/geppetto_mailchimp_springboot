/**
 * Created by Henrikh on 4/8/16.
 */

app.controller('HomeCtrl', ['$scope',
  function ($scope) {

    var self = $scope;

    self.main_slider_items = [
      {
        img: 'assets/media/main-slider/1.jpg'
      },
      {
        img: 'assets/media/main-slider/2.jpg'
      }
    ];

    self.main_slider_options = {
      width: 670,
      height: 345,
      loop: true,
      keyboard: true,
      autoplay: true
    };

    self.another_slider_items = [
      {
        img: 'assets/media/sliders/analytics-1.jpg'
      },
      {
        img: 'assets/media/sliders/analytics-2.png'
      },
      {
        img: 'assets/media/sliders/analytics-3.jpg'
      }
    ];

    self.another_slider_options = {
      width: '100%',
      height: 600,
      loop: true,
      keyboard: true,
      autoplay: true
    };

  }]);