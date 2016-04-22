/**
 * Created by Henrikh on 4/5/16.
 */

app.controller('SurveyBuilderCtrl', ['$scope', '$http', '$compile', '$routeParams', '$timeout', 'RestURL',
  function ($scope, $http, $compile, $routeParams, $timeout, RestURL) {
    var self = $scope;

    /* config */
    self.NOW_EDITING = false;
    self.NEW_QUESTION = false;

    /* keep survey id */
    self.survey_id = $routeParams['id'];

    self.init = function () {
      /* get survey by self.survey_id */
      self.getSurveyByID(self.survey_id);

      /* get questions by self.survey_id */
      self.getQuestionsBySurveyID(self.survey_id);

      /* get question types */
      self.getQuestionTypes();
    };


    // self.survey = {
    //   "status": "U",
    //   "id": 1,
    //   "survey_name": "Survey 1",
    //   "questions": [
    //     {
    //       "id": 1,
    //       "type_name": "Range Slider",
    //       "type_id": 5,
    //       "title": "asfsd",
    //       "answers": [],
    //       "items": [],
    //       "value": 20,
    //       "options": {
    //         "ceil": 50,
    //         "floor": 10
    //       }
    //     },
    //     {
    //       "id": 2,
    //       "type_name": "Add Picture",
    //       "type_id": 4,
    //       "title": "asfsd",
    //       "answers": [],
    //       "items": [
    //         {
    //           "id": 1,
    //           "url": "http://www.iconarchive.com/download/i85541/graphicloads/100-flat/analytics.ico"
    //         },
    //         {
    //           "id": 0,
    //           "url": "http://www.iconarchive.com/download/i85541/graphicloads/100-flat/analytics.ico"
    //         }
    //       ]
    //     }
    //   ],
    //   "client_id": 1,
    //   "client_name": "Company 1",
    //   "country_id": 1,
    //   "country_name": "Country 1",
    //   "product_id": 1,
    //   "product_name": "Product 1",
    //   "survey_description": "Description 1",
    //   "survey_notes": "",
    //   "created_by": 1,
    //   "created_date": 1459967400000,
    //   "updated_by": 1,
    //   "updated_date": 1459967400000
    // };

    /* hard-coded */
    // self.survey = {
    //   "status": "C",
    //   "client_id": 1,
    //   "product_id": 1,
    //   "country_id": 1,
    //   "question_dto": [
    //   {
    //     "id": 1,
    //     "type_name": "PLAIN_TEXT",
    //     "type_id": 1,
    //     "title": "Coke for diet?",
    //     "option_dto": [],
    //     "items_dto": [],
    //     "options": {
    //       "ceil": 50,
    //       "floor": 10
    //     }
    //   },
    //   {
    //     "id": 1,
    //     "type_name": "OPTION_LABEL",
    //     "type_id": 1,
    //     "title": "What Coca Cola products you see in store ?",
    //     "option_dto": [
    //       {
    //         "id": 0,
    //         "name": "Coke"
    //       },
    //       {
    //         "id": 0,
    //         "name": "Fanta"
    //       },
    //       {
    //         "id": 0,
    //         "name": "Diet Coke"
    //       }
    //     ],
    //     "items_dto": [],
    //     "options": {
    //       "ceil": 50,
    //       "floor": 10
    //     }
    //   },
    //   {
    //     "id": 1,
    //     "type_name": "IMAGE_OPTIONS",
    //     "type_id": 1,
    //     "title": "whcih one u like more? ",
    //     "option_dto": [],
    //     "items_dto": [
    //       {
    //         "id": 0,
    //         "url": null
    //       },
    //       {
    //         "id": 0,
    //         "url": null
    //       }
    //     ],
    //     "options": {
    //       "ceil": 50,
    //       "floor": 10
    //     }
    //   },
    //   {
    //     "id": 1,
    //     "type_name": "PLAIN_TEXT",
    //     "type_id": 1,
    //     "title": "why pepsi in color of brown?",
    //     "option_dto": [],
    //     "items_dto": [],
    //     "options": {
    //       "ceil": 50,
    //       "floor": 10
    //     }
    //   }
    // ],
    //   "client_name": "clientName",
    //   "country_name": "countryName",
    //   "product_name": "coca",
    //   "survey_description": "Survey_description1",
    //   "survey_name": "SurveyName1",
    //   "survey_notes": "Survey_notes",
    //   "id": 1
    // };


    /* existing questions */


    self.addQuestion = function () {
      var $editForm = $('.edit');

      /* validation */
      if (self.NOW_EDITING || ($editForm.length && !$editForm.hasClass('hidden'))) {
        /* if editing another question */
        alert('You are currently editing a question. Please complete it before proceeding.');
      } else if (!self.new_question_type) {
        /* if not valid show alert */
        alert('Please choose question type');
      } else {

        /* hide add question modal */
        $('#add_question_modal').modal('hide');

        var new_question = {}, option = {};

        switch (self.new_question_type['id']) {
          /* Single Textbox */
          case 1:
            /* set values */
            new_question['id'] = 0;
            new_question['display_type'] = 'PLAIN_TEXT';
            new_question['type_id'] = self.new_question_type.id;
            new_question['the_question'] = '';
            new_question['survey_id'] = self.survey_id;
            // TODO: remove ???
            option = {};
            option['id'] = 0;
            option['question_id'] = 0;
            option['answer'] = 0;
            option['answer_type'] = 'PLAIN_TEXT';
            new_question['options'] = [];
            new_question['options'].push(option);
            break;
          /* Multiple Choice (Only One Answer) */
          case 2:
            /* set values */
            new_question['id'] = 0;
            new_question['display_type'] = 'OPTION_LABEL';
            new_question['type_id'] = self.new_question_type.id;
            new_question['the_question'] = '';
            new_question['survey_id'] = self.survey_id;
            option = {};
            option['id'] = 0;
            option['question_id'] = 0;
            option['answer'] = 'Answer 1';
            option['answer_type'] = 'OPTION_LABEL';
            var option2 = {};
            option2['id'] = 0;
            option2['question_id'] = 0;
            option2['answer'] = 'Answer 2';
            option2['answer_type'] = 'OPTION_LABEL';
            new_question['options'] = [];
            var option3 = {};
            option3['id'] = 0;
            option3['question_id'] = 0;
            option3['answer'] = 'Answer 3';
            option3['answer_type'] = 'OPTION_LABEL';
            new_question['options'] = [];
            var option4 = {};
            option4['id'] = 0;
            option4['question_id'] = 0;
            option4['answer'] = 'Answer 4';
            option4['answer_type'] = 'OPTION_LABEL';
            new_question['options'] = [];
            new_question['options'].push(option);
            new_question['options'].push(option2);
            new_question['options'].push(option3);
            new_question['options'].push(option4);
            break;
          /* Take Photo */
          case 3:
            /* set values */
            new_question['id'] = 0;
            new_question['display_type'] = 'CAMERA';
            new_question['type_id'] = self.new_question_type.id;
            new_question['the_question'] = '';
            new_question['survey_id'] = self.survey_id;
            // TODO: remove ???
            option = {};
            option['id'] = 0;
            option['question_id'] = 0;
            option['answer'] = 0;
            option['answer_type'] = 'CAMERA';
            new_question['options'] = [];
            new_question['options'].push(option);
            break;
          /* Add Picture */
          case 4:
            /* set values */
            new_question['id'] = 0;
            new_question['display_type'] = 'IMAGE_OPTIONS';
            new_question['type_id'] = self.new_question_type.id;
            new_question['the_question'] = '';
            new_question['survey_id'] = self.survey_id;
            option = {};
            option['id'] = 0;
            option['question_id'] = 0;
            option['answer'] = 'http://www.cooshti.com/store/images/brands/asics-logo-SMALL.jpg';
            option['answer_type'] = 'IMAGE_OPTIONS';
            var option2 = {};
            option2['id'] = 0;
            option2['question_id'] = 0;
            option2['answer'] = 'http://i580.photobucket.com/albums/ss250/sneakyfeetsoccer/NikeLogo-SmallBlack.jpg';
            option2['answer_type'] = 'IMAGE_OPTIONS';
            var option3 = {};
            option3['id'] = 0;
            option3['question_id'] = 0;
            option3['answer'] = 'http://www.p11dorganiser.co.uk/images/puma-logo-small.gif';
            option3['answer_type'] = 'IMAGE_OPTIONS';
            var option4 = {};
            option4['id'] = 0;
            option4['question_id'] = 0;
            option4['answer'] = 'http://i580.photobucket.com/albums/ss250/sneakyfeetsoccer/NikeLogo-SmallBlack.jpg';
            option4['answer_type'] = 'IMAGE_OPTIONS';
            new_question['options'] = [];
            new_question['options'].push(option);
            new_question['options'].push(option2);
            new_question['options'].push(option3);
            new_question['options'].push(option4);
            break;
          case 5:
            new_question['id'] = 0;
            new_question['display_type'] = 'SLIDER';
            new_question['type_id'] = self.new_question_type.id;
            new_question['the_question'] = '';
            new_question['survey_id'] = self.survey_id;
            option = {};
            option['id'] = 0;
            option['question_id'] = 0;
            option['answer'] = 0;
            option['answer_type'] = 'SLIDER';
            new_question['options'] = [];
            new_question['options'].push(option);
            break;
        }

        /* TODO: http request goes here */
        /* add new question to current survey questions */
        console.log(JSON.stringify(new_question));
        $http.post(RestURL.baseURL + 'Question/create_Question', new_question)
          .success(function (response) {
            console.log(response);
            new_question = response;

            /* GET survey by id */
            self.getQuestionsBySurveyID(self.survey_id);

            self.NOW_EDITING = true;
            self.NEW_QUESTION = true;

            /* push new question to the existing questions array */
            // self.questions.push(new_question);
            // console.log(self.questions);
          })
          .error(function (err) {
            console.error(err);
          });
      }
    };

    self.saveQuestion = function (question_id) {
      var valid = false, type;

      var question;

      for (var i = 0; i < self.questions.length; i++) {
        if (self.questions[i].id === question_id) {
          question = self.questions[i];
        }
      }

      /* title validation */
      if (question['the_question']) {
        valid = true;
      }

      /* URL validation */
      var validUrl = true;
      if (question['items']) {
        for (var j = 0; j < question['items'].length; j++) {
          if (!question['items'][j]['url']) {
            validUrl = false;
          }
        }
      }

      /* if new question */
      if (self.new_question_type) {
        type = self.new_question_type['id'];
        /* reset new_question_type */
        self.new_question_type = undefined;
      } else {
        /* for existing questions */
        type = question['type_id'];
      }

      /* DOM for the given question */
      var $question = $('#question_' + question_id);

      if (valid && validUrl) {
        switch (type) {
          case 1:
            /* TODO: http request goes here */
            /* save question data */

            /* hide all except Single Textbox */
            // $question.find('.preview .radio_buttons').addClass('hidden');

            break;
          case 2:
            /* TODO: http request goes here */
            /* save question data */

            /* hide all except Multiple Choice Answers */
            // $question.find('.preview .single_textbox').addClass('hidden');

            break;
          case 3:
            /* TODO: http request goes here */
            /* save question data */

            /* hide all except Take Photo */
            // $question.find('.preview .take_photo').addClass('hidden');

            break;
          case 4:
            /* TODO: http request goes here */
            /* save question data */

            /* hide all except Upload Photo */
            // $question.find('.preview .take_photo').addClass('hidden');

            break;
          case 5:
            /* TODO: http request goes here */
            /* save question data */

            question['value'] = (question['options']['floor'] + question['options']['ceil']) / 2;
            $timeout(function () {
              self.$broadcast('rzSliderForceRender');
            }, 0);

          /* hide all except Upload Photo */
          // $question.find('.preview .take_photo').addClass('hidden');
        }

        $http.put(RestURL.baseURL + 'Question/update_Question', question)
          .success(function (response) {
            console.log('update question: ', response);
            self.getQuestionsBySurveyID(self.survey_id);

            /* hide edit question form */
            $question.find('.edit').addClass('hidden');

            /* show preview */
            $question.find('.preview').removeClass('hidden');

            /* show action buttons (edit, delete) */
            $question.find('.actions').removeClass('hidden');

            self.NEW_QUESTION = false;
          })
          .error(function (err) {
            console.error(err);
          });

        console.log('yaaay');



        /* TODO: http request update survey */

      } else if (valid && !validUrl) {
        /* if image is not selected */
        alert('Please select an image.');
      } else {
        /* if not valid show alert */
        alert('Please enter a title');
      }

      /* reset new question type */
      self.new_question_type = undefined;

      self.NOW_EDITING = false;
      self.NEW_QUESTION = false;
    };

    self.editQuestion = function (question_id) {
      if (self.NOW_EDITING) {
        /* if editing another question */
        alert('You are currently editing a question. Please complete it before proceeding.');
      } else {
        self.NOW_EDITING = true;

        /* DOM for the given question */
        var $question = $('#question_' + question_id);

        /* show edit question form */
        $question.find('.edit').removeClass('hidden');

        /* hide preview */
        $question.find('.preview').addClass('hidden');

        /* hide action buttons (edit, delete) */
        $question.find('.actions').addClass('hidden');
      }
    };

    self.deleteQuestion = function (question_id) {
      /* TODO: http request goes here */
      /* delete question with the given id */

      /* delete question from self.questions array */
      for (var i = 0; i < self.questions.length; i++) {
        if (self.questions[i]['id'] === question_id) {
          self.questions.splice(i, 1);
        }
      }
    };

    /* === Multiple Choice (Only One Answer) ==== */

    self.addRadioAnswer = function (question_id) {
      /* TODO: http request goes here */
      /* get id for new answer */

      for (var i = 0; i < self.questions.length; i++) {
        if (question_id === self.questions[i]['id']) {
          var new_answer = {
            'id': 0,
            'name': 'Answer'
          };
          self.questions[i]['answers'].push(new_answer);
        }
      }
    };

    self.removeRadioAnswer = function (question_id, answer_id) {
      /* TODO: http request goes here */
      /* get id for new answer */

      for (var i = 0; i < self.questions.length; i++) {
        if (question_id === self.questions[i]['id']) {
          for (var j = 0; j < self.questions[i]['answers'].length; j++) {
            if (answer_id === self.questions[i]['answers'][j]['id']) {
              self.questions[i]['answers'].splice(j, 1);
            }
          }
        }
      }
    };

    /* === Add Picture ==== */

    // self.uploadFile = function (input) {
    //   if (input.files && input.files[0]) {
    //     var reader = new FileReader();
    //
    //     reader.onload = function (e) {
    //       var question_id = $(input).parents("li[id^='question_']").attr('id').replace('question_', '');
    //       var item_id = $(input).parents("li[id^='items_']").attr('id').replace('items_', '');
    //
    //       for (var i = 0; i < self.questions.length; i++) {
    //         if (question_id == self.questions[i]['id']) {
    //           for (var j = 0; j < self.questions[i]['items'].length; j++) {
    //             if (item_id == self.questions[i]['items'][j]['id']) {
    //               self.questions[i]['items'][j]['url'] = e.target.result;
    //             }
    //           }
    //         }
    //       }
    //     };
    //
    //     reader.readAsDataURL(input.files[0]);
    //   }
    // };

    self.addPictureItem = function (question_id) {
      /* TODO: http request goes here */
      /* get id for new answer */

      for (var i = 0; i < self.questions.length; i++) {
        if (question_id === self.questions[i]['id']) {
          var new_item = {
            'id': 0,
            'url': ''
          };
          self.questions[i]['items'].push(new_item);
        }
      }
    };

    self.removePictureItem = function (question_id, item_id) {
      /* TODO: http request goes here */
      /* get id for new answer */

      for (var i = 0; i < self.questions.length; i++) {
        if (question_id === self.questions[i]['id']) {
          if (self.questions[i]['items'].length === 1) {
            alert('Not allowed.');
          } else {
            for (var j = 0; j < self.questions[i]['items'].length; j++) {
              if (item_id === self.questions[i]['items'][j]['id']) {
                self.questions[i]['items'].splice(j, 1);
              }
            }
          }
        }
      }
    };

   self.getSurveyByID = function (id) {
      $http.get(RestURL.baseURL + 'Survey_Default_Activity/get_all_Survey_for_desktop')
        .success(function (response) {
          for (var i = 0; i < response.length; i++) {
            if (response[i]['id'] == id) {
              self.survey = response[i];
              console.log(self.survey);
            }
          }
        })
        .error(function (err) {
          console.error(err);
        });
    };

    /* get survey info by self.survey_id */
    self.getQuestionsBySurveyID = function (id) {
      $http.get(RestURL.baseURL + 'Question/get_all_question_by_survey_id/' + id)
        .success(function (response) {
          self.questions = response;
          console.log('q: ', response);
        })
        .error(function (err) {
          console.error(err);
        });
    };

    /* get question types */
    self.getQuestionTypes = function () {
      $http.get(RestURL.baseURL + 'Question/get_all_question_types')
        .success(function (response) {
          self.question_types = response;
          console.log('q_types: ', response);
        })
        .error(function (err) {
          console.error(err);
        });
    };

    self.getValueForSlider = function (value) {
      return undefined;
    };

    self.getOptionsForSlider = function (value) {
      return {"ceil": 50, "floor": 10};
    };

    self.init();
  }]);


app.directive('control', function () {
  return function (scope, element, attrs) {
    if (scope.$last) {
      setTimeout(function () {
        var $question = $(element[0]);
        var question_id = Number($question.attr('id').replace('question_', ''));
        var max_id = $('.questions > li:last-child').attr('id').replace('question_', '');

        if (question_id == max_id && scope.NEW_QUESTION) {
          /* show edit question form */
          $question.find('.edit').removeClass('hidden');

          /* hide preview */
          $question.find('.preview').addClass('hidden');

          /* hide action buttons (edit, delete) */
          $question.find('.actions').addClass('hidden');
        }
      }, 0);
    }
  };
});

// app.directive("fileModel", function () {
//   return {
//     restrict: 'EA',
//     scope: {
//       setFileData: "&"
//     },
//     link: function (scope, ele, attrs) {
//       ele.on('change', function () {
//         scope.$apply(function () {
//           var val = ele[0].files[0];
//           scope.setFileData({value: val});
//           console.log('setFileData: ', scope.setFileData);
//         });
//       });
//     }
//   }
// });

// app.service('fileUpload', ['$http', 'ajaxService',
//   function ($http, ajaxService) {
//
//     this.uploadFileToUrl = function (data) {
//       var data = {}; //file object
//
//       var fd = new FormData();
//       fd.append('file', data.file);
//
//       $http.post("endpoint server path to whom sending file", fd, {
//           withCredentials: false,
//           headers: {
//             'Content-Type': undefined
//           },
//           transformRequest: angular.identity,
//           params: {
//             fd
//           },
//           responseType: "arraybuffer"
//         })
//         .success(function (response, status, headers, config) {
//           console.log(response);
//
//           if (status == 200 || status == 202) {
//             // do whatever in success
//           }
//           else {
//             // handle error in  else if needed
//           }
//         })
//         .error(function (error, status, headers, config) {
//           console.log(error);
//
//           // handle else calls
//         });
//     }
//   }
// ]);