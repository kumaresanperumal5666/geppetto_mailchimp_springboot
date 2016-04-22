/**
 * @author Suresh Palanisamy<br>
 *         <p>
 *         Date Created: 28-July-2015
 *         </p>
 */

app.controller("CampaignController", [
		'$scope',
		'$log',
		'$location',
		'$http',
		'$timeout',
		'$routeParams',
		'$modal',
		'RestURL',
		'CampaignData',
		function($scope, $log, $location, $http, $timeout, $routeParams,
				$modal, RestURL, CampaignData) {

			$scope.campaign = {
				campaignSno : "",
				campaignId : "",
				campaignTitle : "",
				campaignLabel : "",
				campaignDescription : "",
				campaignType : "",
				emailSubject : "",
				fromName : "",
				fromEmail : "",
				toNameType : "",
				emailType : "",
				emailStatus : "",
				emailTemplate : "",
				apiKey : "",
				listId : "",
				createdBy : "",
				createdDate : "",
				updatedBy : "",
				updatedDate : ""
			};

			$scope.template = {
				templateSno : "",
				baseTemplateId : "",
				templateName : "",
				bodyHeader : "",
				bodySubject : "",
				bodyContent : "",
				bodyFooter : "",
				sourceCode : "",
				modifiedCode : "",
				extractedCode : "",
				createdBy : "",
				createdDate : "",
				updatedBy : "",
				updatedDate : ""
			};

			$scope.campaignsList = [];
			$scope.baseTemplatesList = [];
			$scope.templatesList = [];
			$scope.subscribersList = [];
			$scope.pickedSubscribers = [];
			$scope.testEmailIds = [];
			$scope.gridOptions = {};
			
			$scope.openAlertDialog = function(size, msg) {
				var modalInstance = $modal.open({
					animation : true,
					size : size,
					templateUrl : 'app/views/en-US/templates/modals/campaign/alertmessage.html',
					controller : 'ModalController',
					resolve : {
						data : function() {
							return angular.copy(msg);
						},
					}
				});
				modalInstance.result.then(function(dataFromModal) {});
			};
			
			$scope.openDeleteDialog = function(id) {
				var modalInstance = $modal.open({
					animation : true,
					size : 'sm',
					templateUrl : 'app/views/en-US/templates/modals/campaign/deletecampaign.html',
					controller : 'ModalController',
					resolve : {
						data : function() {
							return angular.copy(id);
						},
					}
				});
				modalInstance.result.then(function(dataFromModal) {
					$scope.deleteCampaign(dataFromModal);
				});
			};
			
			$scope.initializeGrid = function() {
				$scope.gridOptions = {
					data : $scope.subscribersList,
					enableSorting : true,
					enableFiltering : true,
					enableCellEditOnFocus : true,
					enableColumnResizing : true,
					enableHorizontalScrollbar : 0,
					paginationPageSizes: [5, 10, 25],
				    paginationPageSize: 5,
					columnDefs : [
							{
								field : 'checkbox',
								name : 'Select',
								displayName : '',
								width : '34',
								enableSorting : false,
								enableFiltering : false,
								enableCellEdit : false,
								enableHiding : false,
								enableColumnMenu : false,
								cellTemplate : '<label class="form-checkbox" style="padding: 8px;">'
										+ '<input type="checkbox" id="checkbox" ng-click="grid.appScope.pickSubscribers(row.entity)">'
										+ '<span></span></label>'
							}, {
								field : 'firstName',
								name : 'First Name',
								width : '250',
							}, {
								field : 'lastName',
								name : 'Last Name',
								width : '250',
							}, {
								field : 'emailAddress',
								name : 'Email Address',
								width : '420',
							} ]
				};
			};
			
			$scope.pickNewTemplate = function(value) {
				if (value == undefined) {
					$("#existingtemplate").removeAttr("disabled");
				} else {
					$("#existingtemplate").attr("disabled", "disabled");
				}
			};
			
			$scope.pickExistingTemplate = function(value) {
				if (value == undefined) {
					$("#newtemplate").removeAttr("disabled");
					$scope.template.bodyHeader = "";
					$scope.template.bodySubject = "";
					$scope.template.bodyContent = "";
					$scope.template.bodyFooter = "";
					$scope.template.sourceCode = "";
					$scope.template.modifiedCode = "";
					$scope.template.extractedCode = "";
				} else {
					$("#newtemplate").attr("disabled", "disabled");
					if ($scope.templatesList.length > 0) {
						for ( var template in $scope.templatesList) {
							if ($scope.templatesList[template].templateSno == value) {
								$scope.template.bodyHeader = $scope.templatesList[template].bodyHeader;
								$scope.template.bodySubject = $scope.templatesList[template].bodySubject;
								$scope.template.bodyContent = $scope.templatesList[template].bodyContent;
								$scope.template.bodyFooter = $scope.templatesList[template].bodyFooter;
								$scope.template.sourceCode = $scope.templatesList[template].sourceCode;
								$scope.template.modifiedCode = $scope.templatesList[template].modifiedCode;
								$scope.template.extractedCode = $scope.templatesList[template].extractedCode;
							}
						}
					}
				}
			};
			
			$scope.addNewCampaign = function() {
				$scope.campaign.emailTemplate = "";
				$location.url('/en-US/campaign/create');
			};
			
			$scope.gotoSendEmail = function() {
				$location.url('/en-US/sendemail/send');
			};

			$scope.editExistingCampaign = function(id) {
				for ( var campaign in $scope.campaignsList) {
					if ($scope.campaignsList[campaign]['campaignSno'] == id) {
						CampaignData.setData($scope.campaignsList[campaign]);
					}
				}
				$location.url('/en-US/campaign/update');
			};
			
			$scope.findAllCampaigns = function() {
				var url = RestURL.baseURL + '/campaign/findAllCampaigns';
				$http({
					method : "GET",
					url : url,
				}).success(function(response) {
					if (response != null) {
						$scope.campaignsList = response;
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.findAllTemplates = function() {
				var url = RestURL.baseURL + '/template/findAllTemplates';
				$http({
					method : "GET",
					url : url,
				}).success(function(response) {
					if (response != null) {
						$scope.baseTemplatesList = response.baseTemplates;
						$scope.templatesList = response.customTemplates;
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.findAllSubscribers = function() {
				var url = RestURL.baseURL + '/subscriber/findAllSubscribers';
				$http({
					method : "GET",
					url : url,
				}).success(function(response) {
					if (response != null) {
						$scope.subscribersList = response;
						$scope.initializeGrid();
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.pickSubscribers = function(data) {				
				if ($scope.pickedSubscribers.length > 0) {
					var remCount = 0;
					for ( var subs in $scope.pickedSubscribers) {
						if ($scope.pickedSubscribers[subs].subscriberSno == data.subscriberSno) {
							$scope.pickedSubscribers.splice(subs, 1);
							remCount += 1;
						}
					}
					if (remCount <= 0) {
						if ($scope.pickedSubscribers.length <= 4) {
							$scope.pickedSubscribers.push(data);
						} else {
							$scope.openAlertDialog('sm', 'Max. limit was reached!');							
						}
					}
				} else {
					$scope.pickedSubscribers.push(data);
				}
			};
			
			$scope.subscribeTheList = function() {
				var url = RestURL.baseURL + '/mailchimpapi/subscribeTheList';
				$http({
					method : "POST",
					url : url,
					data : {
						apiKey : $scope.campaign.apiKey,
						listId : $scope.campaign.listId,
						subscribers : $scope.pickedSubscribers,
					},
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {
					if (response != null) {
						if (response.error_count == 0) {
							var msg = "Subscribers subscribed successfully!"
						} else {
							var msg = "Subscribers subscription failed, due to some internal errors!"
						}
						$scope.openAlertDialog('sm', msg);
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.createCampaign = function() {
				$scope.campaign.emailTemplate = $scope.template;
				var url = RestURL.baseURL + '/campaign/createCampaign';
				$http({
					method : "POST",
					url : url,
					data : $scope.campaign,
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {
					if (response != null) {
						if (response.campaignId != null) {
							$scope.campaign = response;							
							$scope.templatesList.push(response.emailTemplate.templateName);
							$scope.template.templateSno = response.emailTemplate.templateSno;
							$scope.template = response.emailTemplate;
							var msg = "Campaign was created successfully!"
						} else {
							var msg = "Campaign creation was failed, due to some internal errors!"
								console.log("response : ",response)
						}
						$scope.openAlertDialog('sm', msg);
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};

			$scope.updateCampaign = function() {
				$scope.campaign.emailTemplate = $scope.template;
				var url = RestURL.baseURL + '/campaign/updateCampaign';
				$http({
					method : "POST",
					url : url,
					data : $scope.campaign,
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {
					if (response != null) {
						$scope.campaign = response;
						$scope.template = response.emailTemplate;
						var msg = "Campaign was updated successfully!"
					} else {
						var msg = "Campaign updation failed, due to some internal errors!"
					}
					$scope.openAlertDialog('sm', msg);
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.deleteCampaign = function(id) {
				var url = RestURL.baseURL + '/campaign/deleteCampaign?campaignSno=' + id;
				$http({
					method : "GET",
					url : url,
				}).success(function(response) {
					if (response != null) {
						for ( var campaign in $scope.campaignsList) {
							if ($scope.campaignsList[campaign]['campaignSno'] == id) {
								var msg = "Campaign was deleted successfully!";
								$timeout(function() {
									$scope.campaignsList.splice(campaign, 1);
								}, 100);
							} else {
								var msg = "Campaign deletion failed, due to some internal errors!";
							}
						}
						$scope.openAlertDialog('sm', msg);
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.subscriberEmails = [];
			$scope.sendTestEmail = function(size) {
				var msg = "Sending test email with mailchimp.!";
				var modalInstance = $modal.open({
					animation : true,
					size : size,
					templateUrl : 'app/views/en-US/templates/modals/campaign/sendtestemail.html',
					controller : 'ModalController',
					resolve : {
						data : function() {
							return angular.copy(msg);
						},
					}
				});
				modalInstance.result.then(function(dataFromModal) {
					$scope.subscriberEmails.push(dataFromModal.email);
					//$scope.sendSMTPEmail();
					//$scope.testEmailIds = dataFromModal.email.split(","); // Enable this, If you want to send a test email multiple users
					$scope.testEmailIds.push(dataFromModal.email);
					if ($scope.testEmailIds != "") {
						var url = RestURL.baseURL + '/mailchimpapi/sendTestEmail?api_key='
							+ $scope.campaign.apiKey + "&campaign_id=" + $scope.campaign.campaignId + "&emailIds="
							+ $scope.testEmailIds;
						$http({
							method : "GET",
							url : url,
						}).success(function(response) {
							if (response != null) {
								if (response) {
									var msg = "Test email was sent successfully!"
								} else {
									var msg = "Test email was not sent, due to some internal errors!"
								}
								$scope.openAlertDialog('sm', msg);
							}
						}).error(function(response) {
							$log.debug("Request Error: ", response);
						});
					}
				});
			};
			
			$scope.sendCampaign = function() {
				var url = RestURL.baseURL + '/mailchimpapi/sendCampaign?api_key='
						+ $scope.campaign.apiKey + "&campaign_id="
						+ $scope.campaign.campaignId + "&campaign_sno="
						+ $scope.campaign.campaignSno;
				$http({
					method : "GET",
					url : url,
				}).success(function(response) {
					if (response != null) {
						if (response) {
							var msg = "Campaign was sent successfully!"
								$scope.campaign.emailStatus = response;
						} else {
							var msg = "Campaign sending was failed, due to some internal errors!"
						}
						$scope.openAlertDialog('sm', msg);
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.sendSMTPEmail = function() {
				if ($scope.subscriberEmails == "" || $scope.subscriberEmails == null) {
					if ($scope.pickedSubscribers != null) {
						for ( var subs in $scope.pickedSubscribers) {
							$scope.subscriberEmails.push($scope.pickedSubscribers[subs].emailAddress);
						}
					}
				}				
				var url = RestURL.baseURL + '/email/sendSMTPEmail';
				$http({
					method : "POST",
					url : url,
					data : {
						receipiants : $scope.subscriberEmails,
						fromName : $scope.campaign.fromName,
						fromEmail : $scope.campaign.fromEmail,
						emailSubject : $scope.campaign.emailSubject,
						baseTemplateId : $scope.template.baseTemplateId,
						templateSno : $scope.template.templateSno,
						bodyHeader : $scope.template.bodyHeader,
						bodySubject : $scope.template.bodySubject,
						bodyFooter : $scope.template.bodyFooter,
						bodyContent : $scope.template.bodyContent
					},
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {
					if (response != null) {
						if (response) {
							var msg = "Email was sent successfully!"
							$scope.subscriberEmails = [];
						} else {
							var msg = "Email sending was failed, due to some internal errors!"
						}
						$scope.openAlertDialog('sm', msg);
					}
				}).error(function(response) {
					$log.debug("Request Error: ", response);
				});
			};
			
			$scope.goBack = function() {
				$location.url("/en-US/campaigns");
			};
			
			switch ($routeParams['action']) {
			case undefined:
				$scope.findAllCampaigns();
				break;
			case 'create':
				$scope.CampaignBtn = true;
				$scope.findAllTemplates();
				$scope.findAllSubscribers();
				break;
			case 'update':
				$scope.CampaignBtn = false;
				$scope.findAllTemplates();
				$scope.findAllSubscribers();

				$scope.campaign.campaignSno = CampaignData.campaign.campaignSno;
				$scope.campaign.campaignId = CampaignData.campaign.campaignId;
				$scope.campaign.campaignTitle = CampaignData.campaign.campaignTitle;
				$scope.campaign.campaignLabel = CampaignData.campaign.campaignLabel;
				$scope.campaign.campaignDescription = CampaignData.campaign.campaignDescription;
				$scope.campaign.campaignType = CampaignData.campaign.campaignType;
				$scope.campaign.emailSubject = CampaignData.campaign.emailSubject;
				$scope.campaign.fromName = CampaignData.campaign.fromName;
				$scope.campaign.fromEmail = CampaignData.campaign.fromEmail;
				$scope.campaign.toNameType = CampaignData.campaign.toNameType;
				$scope.campaign.emailType = CampaignData.campaign.emailType;
				$scope.campaign.emailStatus = CampaignData.campaign.emailStatus;
				$scope.campaign.emailTemplate = CampaignData.campaign.emailTemplate;
				$scope.campaign.apiKey = CampaignData.campaign.apiKey;
				$scope.campaign.listId = CampaignData.campaign.listId;
				$scope.campaign.createdBy = CampaignData.campaign.createdBy;
				$scope.campaign.createdDate = CampaignData.campaign.createdDate;
				$scope.campaign.updatedBy = CampaignData.campaign.updatedBy;
				$scope.campaign.updatedDate = CampaignData.campaign.updatedDate;

				$scope.template.templateName = CampaignData.campaign.emailTemplate.templateName;
				$scope.template.bodyHeader = CampaignData.campaign.emailTemplate.bodyHeader;
				$scope.template.bodySubject = CampaignData.campaign.emailTemplate.bodySubject;
				$scope.template.bodyContent = CampaignData.campaign.emailTemplate.bodyContent;
				$scope.template.bodyFooter = CampaignData.campaign.emailTemplate.bodyFooter;
				$scope.template.sourceCode = CampaignData.campaign.emailTemplate.sourceCode;
				$scope.template.modifiedCode = CampaignData.campaign.emailTemplate.modifiedCode;
				$scope.template.extractedCode = CampaignData.campaign.emailTemplate.extractedCode;
				$scope.template.createdBy = CampaignData.campaign.emailTemplate.createdBy;
				$scope.template.createdDate = CampaignData.campaign.emailTemplate.createdDate;
				$scope.template.updatedBy = CampaignData.campaign.emailTemplate.updatedBy;
				$scope.template.updatedDate = CampaignData.campaign.emailTemplate.updatedDate;
				
				$timeout(function() {
					$scope.template.templateSno = CampaignData.campaign.emailTemplate.templateSno;
					$scope.template.baseTemplateId = CampaignData.campaign.emailTemplate.baseTemplateId;					
				}, 1000);
				
				break;
			default:
				$location.url('/en-US/campaigns');
			};
			
		} ]);