/**
 * Created by Henrikh on 4/8/16.
 */

app.directive('fotorama', [
  function () {
    return {
      template: '<div class="fotorama"></div>',
      replace: true,
      restrict: 'E',
      scope: {
        options: '=',
        items: '='
      },
      link: function (scope, element, attrs) {
        /* Initialize fotorama with the images */
        scope.items.forEach(function (item) {
          $('<img src="' + item.img + '">').appendTo(element);
        });
        var $fotoramaDiv = $('.fotorama').fotorama();

        /* Pass it the given options */
        $fotoramaDiv.data('fotorama').setOptions(scope.options);
      }
    };
  }
]);