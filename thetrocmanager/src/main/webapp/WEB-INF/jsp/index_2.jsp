<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thetroc Manager</title>
 <link rel="icon" href="img/logo3.png"   type="image/x-icon" />
 <!-- MDB -->
<link rel="stylesheet" href="css/mdb.min.css" />
<script type="text/javascript" src="/js2/angular.min.js"></script>
</head>
<body style="background-image: url('img/background1.jpg');background-size:cover;background-repeat:no-repeat;background-attachment:fixed;">
	<div ng-app="home"  ng-controller="homecontroller">
		<h1 class="text-center text-white mt-3 mb-3" style="font-size:2.5em;">ThetrocManager</h1>
		<div class='row'>
			<div class='col-sm-3'>
				<!--    -->
				<div style="background-color:white; padding:15px; margin-left:3px;">
					<h3 class="text-center mt-2 mb-3" style="font-size:0.9em;color:#48D1CC;margin:5px;">search for a subject or group of subject on the filter below</h3>
					<div class='form-group mb-2' >
						<label for="type"style="font-size:0.8em;" >Type</label>
						<select class="form-control" id="type" ng-model="type" style="font-size:0.8em;" ng-change="typechange()" name="type" >
							<option>sellers</option>
							<option>buyers</option>	
						</select>
					</div>
					<div class='form-outline mb-2' ng-show = "pseudoshow">
						<input type='text' name='pseudo'  id='pseudo' style="font-size:0.8em;"  class='form-control'/>
						<label for='pseudo' class='form-label' style="font-size:0.8em;">pseudo</label>
					</div>
					<div class='form-outline mb-2' ng-show = "emailshow">
						<input type='text' name='email' id='email' style="font-size:0.8em;"  class='form-control'/>
						<label for='email' class='form-label' style="font-size:0.8em;">email</label>
					</div>
					<div class='form-outline mb-2' ng-show="lastnameshow">
						<input type='text' name='name' id='name'  style="font-size:0.8em;" class='form-control'/>
						<label for='name' class='form-label' style="font-size:0.8em;">lastname</label>
					</div>
					<div class='form-outline mb-2'  ng-show ="firstnameshow">
						<input type='text' name='vorname' id='vorname' style="font-size:0.8em;"  class='form-control'/>
						<label for='vorname' class='form-label' style="font-size:0.8em;">firstname</label>
					</div>
					
					<div class='form-outline mb-2' ng-show ="companyshow" >
						<input type='text' name='nameunternehmen'  style="font-size:0.8em;" id='nameunternehmen' class='form-control'/>
						<label for='nameunternehmen' class='form-label' style="font-size:0.8em;">Company name</label>
					</div>
					<button class="mb-2 mt-1" ng-click="searchfunc()" style="width:200px; border:1px solid #48D1CC;border-radius:200px; padding:5px; font-size:0.9em;background-color:#48D1CC;color: white; margin:auto;">search</button>
				</div>
			</div>
			<div class='col-sm-9'>
				<div class="table-responsive" id="tabcontent">
					<table class="table table-dark table-stripped">
						<thead>
							<tr>
								<th>firstname</th>
								<th>lastname</th>
								<th>email</th>
								<th>phone</th>
								<th>company name</th>
								<th>status</th>
								<th>date registration</th>
								<th>activate</th>
								<th>deactivate</th>
								<th>delete</th>
								<th>detail</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="seller in sellerlist">
								<td>{{seller.vorname}}</td>
								<td>{{seller.name}}</td>
								<td>{{seller.email}}</td>
								<td>{{seller.telefon}}</td>
								<td>{{seller.status}}</td>
								<td>{{seller.datum}}</td>
								<td>
									<button ng-click="activate(seller.idverkaufer)" style="width:150px; border:1px solid #228B22;border-radius:200px; padding:2px; font-size:0.9em;background-color:#228B22;color: white; margin:auto;">activate</button>
								</td>
								<td>
									<button ng-click="deactivate(seller.idverkaufer)" style="width:150px; border:1px solid #FF8C00;border-radius:200px; padding:2px; font-size:0.9em;background-color:#FF8C00;color: black; margin:auto;">deactivate</button>
								</td>
								<td>
									<button ng-click="delete(seller.idverkaufer)" style="width:150px; border:1px solid red;border-radius:200px; padding:2px; font-size:0.9em;background-color:red;color: white; margin:auto;">delete</button>
								</td>
								<td>
									<button ng-click="detail(seller.idverkaufer)" style="width:150px; border:1px solid #6495ED;border-radius:200px; padding:2px; font-size:0.9em;background-color:#6495ED;color: white; margin:auto;">details</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
			</div>
		</div>
		

	</div>
	
 <!-- MDB -->
 	<script type="text/javascript" src="js/mdb.min.js"></script>
	<script>
	
	
		var app = angular.module("home",[]);
		app.controller("homecontroller", function($scope,$http){
			
			$scope.typechange = function (){
				let type = $scope.type;
				if(type=="buyers"){
					$scope.companyshow = false;
					$scope.firstnameshow = true;
					$scope.lastnameshow = true;
					$scope.emailshow = true;
					$scope.pseudoshow= true;
				}else if(type=="sellers"){
					$scope.companyshow = true;
					$scope.firstnameshow = true;
					$scope.lastnameshow = true;
					$scope.emailshow = true;
					$scope.pseudoshow= false;
				}else{
					$scope.companyshow = false;
					$scope.firstnameshow = false;
					$scope.lastnameshow = false;
					$scope.emailshow = false;
					$scope.pseudoshow= false;
				}
			};
			
			$scope.searchfunc = function(){
				$http.get("/allsellers").then(function(response){
					$scope.status = response.status;
					alert(JSON.parse(response.data).length);
					//$scope.sellerlist = response.data;
				});
				
			};
			
			
			
		});

	</script>
</body>
</html>