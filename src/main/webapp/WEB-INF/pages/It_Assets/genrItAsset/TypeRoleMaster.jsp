<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<div class="col-md-12" align="center">
	<div class="card">
		<div class="card-header">
			<h5>
				TYPE ROLE MASTER<br>
			</h5>
		</div>
	</div>
</div>

<form:form name="saveForm" id="saveForm" action="Type_RoleMaster_Action"
	method="post" class="form-horizontal" commandName="Type_RoleMasterCMD">
	<div id="recommendedDiv" class="card">
		<div align="center">
			<table id="getSearch_census"
				class="table no-margin table-striped table-hover table-bordered report_print_scroll">
				<thead>
					<tr>
						<th width="10%" style="text-align: center;">Unit Type</th>
						<th width="10%" style="text-align: center;">Role Name</th>
						<th width="15%" style="text-align: center;">Apply Hierarchy</th>
						<th width="10%" style="text-align: center;">Level Of
							Hierarchy</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td width="10%"
								style="text-align: center; vertical-align: middle;" id="unitType${num.index}">${item[1]}</td>
							<td width="10%"
								style="text-align: center; vertical-align: middle;"><input
								id="role_name${num.index}" name="role_name${num.index}" value="${item[2]}"
								type="text" placeholder="Search Role Name" maxlength="250"
								class="form-control role_name" data-index="${num.index}"/></td>
							<td width="10%"
								style="text-align: center; vertical-align: middle;">
								<div class="form-check-inline form-check">
									<label for="answerYes${num.index}" class="form-check-label">
										<input type="radio" id="answerYes${num.index}"
										name="answer${num.index}" value="Yes" class="form-check-input applyHierarchy"
										data-index="${num.index}" onclick="toggleHierarchy(${num.index}, true)"
										${item[3] == 'Yes' ? 'checked' : ''}>Yes
									</label>   <label for="answerNo${num.index}"
										class="form-check-label"> <input type="radio"
										id="answerNo${num.index}" name="answer${num.index}" value="No"
										class="form-check-input applyHierarchy"
										data-index="${num.index}" onclick="toggleHierarchy(${num.index}, false)"
										${item[3] == 'No' ? 'checked' : ''}>No
									</label>
								</div>
							</td>
							<td width="10%"
								style="text-align: center; vertical-align: middle;"><select
								id="level_of_hierarchy${num.index}"
								name="level_of_hierarchy${num.index}" class="form-control levelOfHierarchy"
								data-index="${num.index}" ${item[3] == 'No' ? 'disabled' : ''}>
									<option value="0">--Select--</option>
									<option value="Command" ${item[4] == 'Command' ? 'selected' : ''}>Command</option>
									<option value="Corps" ${item[4] == 'Corps' ? 'selected' : ''}>Corps</option>
									<option value="Division" ${item[4] == 'Division' ? 'selected' : ''}>Division</option>
									<option value="Brigade" ${item[4] == 'Brigade' ? 'selected' : ''}>Brigade</option>
							</select></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="" align="center" id="savebtndiv">
				<div class="card-header" style="height: 50px;">
					<a href="typewiseRoleMaster" class="btn btn-success btn-sm">Clear</a>
					 <input id="saveid" name="saveid"
						type="button" class="btn btn-primary btn-sm add-to-recommended"
						value="Save" onclick="saveData();">
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>

	$('input[id^="role_name"]').each(
		function() {
			var roleNameField = $(this);
			var currentIndex = roleNameField.attr('id').replace('role_name', '');

			roleNameField.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getRole_name?" + key + "=" + value,
						data : {
							Role_name : roleNameField.val()
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							var enc = data[length].substring(0, 16);
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							var dataCountry1 = susval.join("|");
							var myResponse = [];
							$.each(dataCountry1.toString().split("|"),
									function(i, e) {
										var newE = e.substring(0, roleNameField.val().length);
										if (e.toLowerCase().includes(roleNameField.val().toLowerCase())) {
											myResponse.push(e);
										}
									});
							response(myResponse);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {
					if (ui.item) {
						return true;
					} else {
						alert("Please Enter Approved Role Name.");
						roleNameField.val("");
						roleNameField.focus();
						return false;
					}
				},
				select : function(event, ui) {

				}
			});
		});

	function toggleHierarchy(index, isEnabled) {
		var selectElement = document.getElementById('level_of_hierarchy' + index);
		selectElement.disabled = !isEnabled;
		if (!isEnabled) {
			selectElement.value = "0";
		}
	}



	function saveData() {
		var data = [];
		var rowCount = $('table#getSearch_census tbody tr').length;

		for (var i = 0; i < rowCount; i++) {
			var unitType = $('#unitType' + i).text();			
			var roleName = $('#role_name' + i).val();
			var applyHierarchy = $('input[name="answer' + i + '"]:checked').val();
			var levelOfHierarchy = $('#level_of_hierarchy' + i).val();

		    if (!roleName) {
	            alert("Please fill Role Name for Unit Type: " + unitType);
	            $('#role_name' + i).focus();
	            return; 
	        }

		     if (applyHierarchy === "Yes" && levelOfHierarchy === "0") {
		            alert("Please select Level of Hierarchy for Unit Type: " + unitType);
		            $('#level_of_hierarchy' + i).focus();
		            return; 
		        }
			
			
			data.push({
				unitType:unitType,
				roleName : roleName,
				applyHierarchy : applyHierarchy,
				levelOfHierarchy : levelOfHierarchy
			});
		}

	
		$.ajax({
			type : 'POST',
			url : 'Type_RoleMaster_Action',
			data : JSON.stringify(data), 
			contentType : 'application/json',
	        headers: {
	            'X-CSRF-TOKEN': '${_csrf.token}'
	        },
			success : function(response) {
				alert(response); 				
				//$('#saveForm')[0].reset(); 
			},
			error : function(xhr, status, error) {
				console.error("Error saving data: " + error);
				alert("Error saving data. Please check the console for details.");
			}
		});
	}
</script>